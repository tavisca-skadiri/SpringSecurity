package com.tavisca.gce.repository;

import com.tavisca.gce.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String userName);
}