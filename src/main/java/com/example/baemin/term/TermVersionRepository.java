package com.example.baemin.term;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TermVersionRepository extends JpaRepository<TermVersion, TermVersion.TermVersionId> {
    @Query("SELECT tv FROM TermVersion tv WHERE tv.termId = :termId ORDER BY tv.version DESC")
    List<TermVersion> findByTermIdOrderByVersionDesc(@Param("termId") String termId);

    default Optional<TermVersion> findLatestVersion(String termId) {
        List<TermVersion> versions = findByTermIdOrderByVersionDesc(termId);
        return versions.isEmpty() ? Optional.empty() : Optional.of(versions.get(0));
    }

    @Query("SELECT tv FROM TermVersion tv WHERE (tv.termId, tv.version) IN " +
            "(SELECT tv2.termId, MAX(tv2.version) FROM TermVersion tv2 GROUP BY tv2.termId)")
    List<TermVersion> findAllLatestVersions();
}