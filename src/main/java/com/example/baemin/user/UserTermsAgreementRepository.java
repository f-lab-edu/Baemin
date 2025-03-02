package com.example.baemin.user;

import com.example.baemin.user.Entity.UserTermsAgreement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTermsAgreementRepository extends JpaRepository<UserTermsAgreement, Long> {
}