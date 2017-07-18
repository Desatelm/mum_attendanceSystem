package edu.mum.cs.projects.attendance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.mum.cs.projects.attendance.domain.entity.Users;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {
	Optional<Users> findByName(String username);
	Users findByStudentId(String studentId);
}
