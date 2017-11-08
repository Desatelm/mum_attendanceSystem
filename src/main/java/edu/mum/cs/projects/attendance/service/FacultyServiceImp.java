package edu.mum.cs.projects.attendance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.cs.projects.attendance.domain.entity.Faculty;
import edu.mum.cs.projects.attendance.repository.FacultyRepository;

@Service
public class FacultyServiceImp implements FacultyService {

	@Autowired
	FacultyRepository facultyRepository;

	@Override
	public List<Faculty> getAll() {

		return (List<Faculty>) facultyRepository.findAll();
	}

	@Override
	public Faculty getFacultyById(Long id) {

		return facultyRepository.findById(id);
	}

	@Override
	public void createFaculty(Faculty faculty) {
		facultyRepository.save(faculty);
	}

}
