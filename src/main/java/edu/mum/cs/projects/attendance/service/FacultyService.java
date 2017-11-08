package edu.mum.cs.projects.attendance.service;

import java.util.List;

import edu.mum.cs.projects.attendance.domain.entity.Faculty;

public interface FacultyService {
	List<Faculty> getAll();

	Faculty getFacultyById(Long id);

	void createFaculty(Faculty faculty);
}
