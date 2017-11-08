package edu.mum.cs.projects.attendance.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.mum.cs.projects.attendance.domain.entity.Users;
import edu.mum.cs.projects.attendance.service.FacultyService;
import edu.mum.cs.projects.attendance.service.StudentService;

@Controller
// @Transactional(propagation = Propagation.REQUIRES_NEW)
public class WelcomeController {
	@Autowired
	private StudentService studentService;
	@Autowired
	private FacultyService facultyService;

	@RequestMapping("/welcome")
	public String dashboard(Model model, HttpSession session) {

		Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String has_Role = user.getRoles().getRole();
		String lastName = "";

		System.out.println(user.getEmail());
		System.out.println(user.getFacultyId());

		if (has_Role.equals("STUDENT")) {
			lastName = studentService.getStudentsById(user.getStudentId()).getLastName();
		} else if ((has_Role.equals("FACULTY"))) {
			System.out.println(user.getFacultyId());
			lastName = facultyService.getFacultyById(user.getFacultyId()).getLastName();

		} else {
			lastName = "Admin";
		}

		session.setAttribute("lastName", lastName);
		session.setAttribute("username", user.getName());

		return "welcome";
	}
}
