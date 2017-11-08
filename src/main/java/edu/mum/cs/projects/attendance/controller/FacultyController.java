package edu.mum.cs.projects.attendance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.mum.cs.projects.attendance.domain.entity.CourseOffering;
import edu.mum.cs.projects.attendance.domain.entity.Users;
import edu.mum.cs.projects.attendance.repository.FacultyRepository;
import edu.mum.cs.projects.attendance.service.CourseService;
import edu.mum.cs.projects.attendance.service.EnrollmentService;

@Controller
public class FacultyController {
	@Autowired
	EnrollmentService enrollmentService;

	@Autowired
	CourseService courseService;

	@Autowired
	FacultyRepository facultyRepository;

	@RequestMapping(value = "/faculty/courseList")
	public String facultyCourseList(Model model, Authentication authentication) {
		Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(user);
		Long facultyId = user.getFacultyId();

		List<CourseOffering> courseOfferingList = courseService.getCourseOfferingsPastSixMonths(facultyId);

		model.addAttribute("courseOfferingList", courseOfferingList);
		return "facultyCourseList";
	}
}
