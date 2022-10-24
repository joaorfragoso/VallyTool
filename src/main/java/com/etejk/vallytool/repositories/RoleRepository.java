package com.etejk.vallytool.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.etejk.vallytool.entities.RoleModel;

@Repository
public interface RoleRepository extends JpaRepository<RoleModel, Integer>{
	@Query("Select r FROM RoleModel r WHERE r.roleName = ?1")
	RoleModel findByRoleName(String role);
}
