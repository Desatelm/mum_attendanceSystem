package edu.mum.cs.projects.attendance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.cs.projects.attendance.domain.entity.Enrollment;
import edu.mum.cs.projects.attendance.domain.entity.Student;
import edu.mum.cs.projects.attendance.repository.EnrollmentRepository;
import edu.mum.cs.projects.attendance.repository.StudentRepository;
import edu.mum.cs.projects.attendance.util.IDNumberUtil;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
	@Autowired
	EnrollmentRepository enrollmentRepository;

	@Autowired
	StudentRepository studentRepository;

	@Override
	public Iterable<Enrollment> getEnrolledCoursesByStudentId(String id) {
		String studentId = IDNumberUtil.convertToStudentId(id);
		Student student = studentRepository.findBystudentId(studentId);
		return enrollmentRepository.findByStudent(student);
	}

	@Override
	public Iterable<Enrollment> getCourseOfferingTaught(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
