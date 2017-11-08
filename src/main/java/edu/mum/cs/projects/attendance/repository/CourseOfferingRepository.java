package edu.mum.cs.projects.attendance.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.mum.cs.projects.attendance.domain.entity.Course;
import edu.mum.cs.projects.attendance.domain.entity.CourseOffering;
import edu.mum.cs.projects.attendance.domain.entity.Faculty;

@Repository
public interface CourseOfferingRepository extends CrudRepository<CourseOffering, Integer> {
	List<CourseOffering> findByStartDate(Date startDate);

	List<CourseOffering> findByCourse(Course course);

	CourseOffering findById(long id);

	List<CourseOffering> findByFaculty(Faculty course);

}
