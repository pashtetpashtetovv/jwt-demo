package com.example.jwtdemo.repository;

import com.example.jwtdemo.model.ERole;
import com.example.jwtdemo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(ERole name);

    Boolean existsByName(ERole name);
}
