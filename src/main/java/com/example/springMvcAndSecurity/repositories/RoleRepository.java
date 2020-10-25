package com.example.springMvcAndSecurity.repositories;

import com.example.springMvcAndSecurity.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findByName(String name);
}
