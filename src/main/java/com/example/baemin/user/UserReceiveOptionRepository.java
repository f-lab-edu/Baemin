package com.example.baemin.user;

import com.example.baemin.user.Entity.UserReceiveOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReceiveOptionRepository extends JpaRepository<UserReceiveOption, Long> {
}