package edu.mum.cs.projects.attendance.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mum.cs.projects.attendance.domain.entity.CourseOffering;
import edu.mum.cs.projects.attendance.domain.entity.Users;
import edu.mum.cs.projects.attendance.service.CourseService;
import edu.mum.cs.projects.attendance.service.StudentService;

@Controller
public class CourseController {
	@Autowired
	private CourseService courseService;
	@Autowired
	private StudentService studentService;
	//@PreAuthorize("hasAnyRole('FACULTY', 'STAFF', 'ADMIN')")
	@GetMapping(value = "/")
	public String get(Model model) {		
		List<CourseOffering> courseOfferings = new ArrayList<>();
		
		Users user = (Users)SecurityContextHolder.getContext().getAuthentication().getPrincipal();		 
		String name = studentService.getStudentById(user.getStudentId()).getLastName();
	    				
		model.addAttribute("name", name);
		
		model.addAttribute("courseOfferings", courseOfferings);
		return "welcome";
	}

	@PostMapping(value = "/course")
	public String getAll(Model model, @RequestParam(value = "lastName") String lastName) {
		List<CourseOffering> allCourseOfferings = courseService.getAll();
		List<CourseOffering> courseOfferings = new ArrayList<>();
		System.out.println(lastName);
		for (CourseOffering courseOffering : allCourseOfferings) {
			if (courseOffering.getFaculty().getLastName().equals(lastName)) {
				courseOfferings.add(courseOffering);
			}
		}
		model.addAttribute("courseOfferings", courseOfferings);
		return "welcome";
	}
}
