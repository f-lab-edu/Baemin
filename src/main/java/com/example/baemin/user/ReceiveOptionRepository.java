package com.example.baemin.term;

import com.example.baemin.user.Entity.ReceiveOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiveOptionRepository extends JpaRepository<ReceiveOption, Long> {
    List<ReceiveOption> findByTermIdAndVersion(String termId, String version);
}