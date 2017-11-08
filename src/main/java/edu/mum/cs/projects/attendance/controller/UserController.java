package edu.mum.cs.projects.attendance.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import edu.mum.cs.projects.attendance.domain.entity.Faculty;
import edu.mum.cs.projects.attendance.domain.entity.Role;
import edu.mum.cs.projects.attendance.domain.entity.Student;
import edu.mum.cs.projects.attendance.domain.entity.Users;
import edu.mum.cs.projects.attendance.repository.RoleRepository;
import edu.mum.cs.projects.attendance.service.FacultyService;
import edu.mum.cs.projects.attendance.service.StudentService;
import edu.mum.cs.projects.attendance.service.UserService;

@Controller
@Transactional(propagation = Propagation.REQUIRES_NEW)

public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	StudentService studentService;
	@Autowired
	FacultyService facultyService;
	@Autowired
	RoleRepository roleRepository;

	@RequestMapping(value = "/user/get", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> findUser(Model model, @RequestParam String userName, HttpServletResponse response)
			throws IOException {
		String user = "";
		if (null == userService.getUserByUserName(userName)) {
			Users mockUser = new Users();
			mockUser.setActive(000000);
			mockUser.setName(userName);
			user = new Gson().toJson(mockUser);
		} else {
			user = new Gson().toJson(userService.getUserByUserName(userName));
		}
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<String>(user, httpHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/get/{username}", method = RequestMethod.GET)
	public String getUser(Model model, @PathVariable String username) {
		model.addAttribute("user", userService.getUserByUserName(username));
		return "userProfile";
	}

	@RequestMapping(value = "/user/getStudent/", method = RequestMethod.GET)
	public ResponseEntity<String> getStudent(Model model, @RequestParam String studentId,
			@RequestParam String username) {

		String student = new Gson().toJson(studentService.getStudentsById(studentId));
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<String>(student, httpHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/getFaculty/", method = RequestMethod.GET)
	public ResponseEntity<String> getFaculty(Model model, @RequestParam Long facultyId, @RequestParam String username) {

		String faculty = new Gson().toJson(facultyService.getFacultyById(facultyId));
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<String>(faculty, httpHeaders, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/editStudent/{studentId}/{username}", method = RequestMethod.GET)
	public String editStudent(Model model, @PathVariable String studentId, @PathVariable String username) {

		model.addAttribute("user", userService.getUserByUserName(username));
		model.addAttribute("student", studentService.getStudentsById(studentId));
		return "editUser";
	}

	@RequestMapping(value = "/user/editFaculty/{facultyId}/{username}", method = RequestMethod.GET)
	public String editFaculty(Model model, @PathVariable Long facultyId, @PathVariable String username) {

		model.addAttribute("user", userService.getUserByUserName(username));
		model.addAttribute("faculty", facultyService.getFacultyById(facultyId));
		return "editUser";
	}

	@RequestMapping(value = "/user/changTofacultyRole/{id}", method = RequestMethod.POST)
	@Transactional
	public ModelAndView updateUserRole(@PathVariable int id, @RequestParam String role) {

		Users user = userService.getUserByID(id);

		Role roles = roleRepository.findByRole(role);

		long facultyId = facultyService.getAll().size() + 1;
		Faculty faculty = new Faculty();
		faculty.setFirstName(studentService.getStudentsById(user.getStudentId()).getFirstName());
		faculty.setLastName(studentService.getStudentsById(user.getStudentId()).getLastName());
		faculty.setId(facultyId);

		user.setName(faculty.getLastName() + facultyId);
		user.setStudentId(null);
		user.setFacultyId(facultyId);
		user.setRoles(roles);

		facultyService.createFaculty(faculty);
		userService.createUser(user);
		ModelAndView model = new ModelAndView("confirmation");
		model.addObject("user", user);
		model.addObject("faculty", faculty);

		return model;
	}

	@RequestMapping(value = "/user/changeToStudentRole/{id}", method = RequestMethod.POST)
	@Transactional
	public ModelAndView updateRole(@PathVariable int id, @RequestParam String role) {

		Users user = userService.getUserByID(id);

		Role roles = roleRepository.findByRole(role);

		Student student = new Student();
		student.setFirstName(facultyService.getFacultyById(user.getFacultyId()).getFirstName());
		student.setLastName(facultyService.getFacultyById(user.getFacultyId()).getLastName());
		student.setStudentId("000-" + facultyService.getFacultyById(user.getFacultyId()).getId() + "-000");
		student.setEntryDate(new Date());

		user.setName(("000" + facultyService.getFacultyById(user.getFacultyId()).getId()));
		user.setStudentId(student.getStudentId());
		user.setFacultyId(null);
		user.setEmail(student.getLastName() + "@mum.edu");
		user.setRoles(roles);

		studentService.createStudent(student);
		userService.createUser(user);
		ModelAndView model = new ModelAndView("confirmation");
		model.addObject("user", user);
		model.addObject("student", student);

		return model;
	}

}
