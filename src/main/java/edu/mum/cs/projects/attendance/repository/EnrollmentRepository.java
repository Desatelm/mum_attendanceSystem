package edu.mum.cs.projects.attendance.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.mum.cs.projects.attendance.domain.entity.Enrollment;
import edu.mum.cs.projects.attendance.domain.entity.Student;

@Repository
public interface EnrollmentRepository extends CrudRepository<Enrollment, Long> {
	Iterable<Enrollment> findByStudent(Student student);

}
