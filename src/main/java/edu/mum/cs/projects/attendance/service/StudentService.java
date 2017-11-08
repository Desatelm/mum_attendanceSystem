package edu.mum.cs.projects.attendance.service;

import java.util.List;

import edu.mum.cs.projects.attendance.domain.entity.Student;

/**
 * <h1>Maharishi University of Management<br/>
 * Computer Science Department</h1>
 * 
 * <p>
 * Service layer facade, hides away details of dataaccess layer from client.
 * </p>
 *
 * @author Payman Salek
 * 
 * @version 2.0.0
 * @since 1.0.0
 */
public interface StudentService {

	static final StudentService INSTANCE = new StudentServiceImpl();

	List<Student> getStudentsByEntry(String entryDate);

	// added fire group
	Student getStudentsById(String id);

	public List<Student> getAllStudents();

	String getBarcodeId(String studentId);

	static StudentService getInstance() {
		return INSTANCE;
	}

	void createStudent(Student student);

}
