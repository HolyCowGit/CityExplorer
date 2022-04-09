package com.city.explorer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.city.explorer.model.Role;


public interface RoleRepository extends JpaRepository<Role, Integer> {

}
