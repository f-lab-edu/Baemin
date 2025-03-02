package com.example.baemin.term;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TermRepository extends JpaRepository<Term, Term.TermId> {
    List<Term> findAll();

    @Query("SELECT t FROM Term t WHERE t.termId = :termId AND t.version = " +
            "(SELECT MAX(t2.version) FROM Term t2 WHERE t2.termId = :termId)")
    Optional<Term> findLatestVersion(@Param("termId") String termId);

    @Query("SELECT t FROM Term t WHERE (t.termId, t.version) IN " +
            "(SELECT t2.termId, MAX(t2.version) FROM Term t2 GROUP BY t2.termId)")
    List<Term> findAllLatestVersions();
}