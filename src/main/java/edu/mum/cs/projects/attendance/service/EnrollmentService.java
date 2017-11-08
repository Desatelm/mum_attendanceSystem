package edu.mum.cs.projects.attendance.service;

import edu.mum.cs.projects.attendance.domain.entity.Enrollment;

public interface EnrollmentService {
	Iterable<Enrollment> getEnrolledCoursesByStudentId(String id);

	Iterable<Enrollment> getCourseOfferingTaught(String id);

}
