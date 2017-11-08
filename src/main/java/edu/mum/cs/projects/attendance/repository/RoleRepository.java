package edu.mum.cs.projects.attendance.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.mum.cs.projects.attendance.domain.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
	Role findByRole(String role);
}
