package edu.mum.cs.projects.attendance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.mum.cs.projects.attendance.domain.entity.Student;
import edu.mum.cs.projects.attendance.service.EnrollmentService;
import edu.mum.cs.projects.attendance.service.StudentService;
import edu.mum.cs.projects.attendance.util.IDNumberUtil;

@Controller
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class StudentController {

	@Autowired
	StudentService studentService;

	@Autowired
	EnrollmentService enrollmentService;

	@RequestMapping(value = "/student/edit/{id}", method = RequestMethod.GET)
	public String editStudentForm(@PathVariable String id, Model model) {
		Student student = studentService.getStudentsById(id);
		model.addAttribute("student", student);
		return "editStudent";
	}

	@RequestMapping(value = "/student/find", method = RequestMethod.GET)
	public String findStudentByIdform(Model model) {
		return "findstudent";
	}

	@RequestMapping(value = "/student/get", method = RequestMethod.GET)
	public String findStudentById(@RequestParam("studentId") String id, Model model) {
		Student student = studentService.getStudentsById(id);
		model.addAttribute("student", student);
		return "findstudent";
	}

	@RequestMapping(value = "/student/add", method = RequestMethod.GET)
	public String getStudentForm(@ModelAttribute("student") Student student) {
		return "addStudent";
	}

	@RequestMapping(value = "/student/list", method = RequestMethod.GET)
	public String getStudentsByEntry(@RequestParam("entryDate") String entryDate, Model model) {

		List<Student> students = studentService.getStudentsByEntry(entryDate);
		model.addAttribute("students", students);
		return "studentsList";
	}

	@RequestMapping(value = "/student/allList", method = RequestMethod.GET)
	public String getAllStudents(Model model) {

		List<Student> students = studentService.getAllStudents();
		model.addAttribute("students", students);
		return "studentsList";
	}

	@RequestMapping(value = "/student/Courselist")
	public String getStudentCourseList(String studentid, Model model, Authentication authentication) {

		model.addAttribute("enrolledCourses",
				enrollmentService.getEnrolledCoursesByStudentId(authentication.getName()));
		String studentId = IDNumberUtil.convertToStudentId(authentication.getName());
		System.out.println("------------------" + studentId);
		model.addAttribute("studentID", studentId);
		return "studentCoursesTaken";
	}

	@RequestMapping(value = "/student/CourselistWithId/{studentId}")
	public String getStudentCourseListByStudentID(@PathVariable("studentId") String studentId, Model model) {

		model.addAttribute("enrolledCourses", enrollmentService.getEnrolledCoursesByStudentId(studentId));
		model.addAttribute("studentID", studentId);

		return "studentCoursesTaken";
	}
}
