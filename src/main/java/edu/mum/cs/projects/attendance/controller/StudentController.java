package edu.mum.cs.projects.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import edu.mum.cs.projects.attendance.service.StudentService;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping(value = "/students")
	public String getAll(Model model) {
		model.addAttribute("students", studentService.getStudentsByEntry("2016-08-04"));
		return "studentsList";
	}

}
