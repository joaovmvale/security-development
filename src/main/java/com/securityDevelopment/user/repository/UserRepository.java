package com.securityDevelopment.user.repository;

import com.securityDevelopment.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Integer> {
}
