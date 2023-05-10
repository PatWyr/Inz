package com.aws.inzynierka.repository;

import com.aws.inzynierka.model.entity.Role;
import com.aws.inzynierka.model.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(UserRole name);
}
