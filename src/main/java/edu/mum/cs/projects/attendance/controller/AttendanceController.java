package edu.mum.cs.projects.attendance.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import edu.mum.cs.projects.attendance.domain.StudentAttendance;
import edu.mum.cs.projects.attendance.domain.entity.CourseOffering;
import edu.mum.cs.projects.attendance.service.AttendanceService;
import edu.mum.cs.projects.attendance.service.CourseService;
import edu.mum.cs.projects.attendance.service.StudentService;

@Controller
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;
	@Autowired
	private CourseService courseService;

	@GetMapping(value = "/attendance/{id}")
	public String getAll(Model model, @PathVariable Long id) {

		List<StudentAttendance> studentsAttendance = new ArrayList<>();

		List<CourseOffering> allCourseOfferings = courseService.getAll();
		for (CourseOffering courseOffering : allCourseOfferings) {

			if (courseOffering.getId().equals(id)) {
				for (CourseOffering courseOfferingg : courseService
						.getCourseOfferings(courseOffering.getStartDate().toString())) {
					if (courseOfferingg.getId().equals(id))
						studentsAttendance = attendanceService.createAttendanceReportForOffering(courseOfferingg);
				}
			}
		}

		System.out.println(studentsAttendance.size());
		double average = 0;
		double sum = 0;
		String faculty = null;
		for (StudentAttendance studentAttendance : studentsAttendance) {
			sum += studentAttendance.getMeditaionPercentage();
			faculty = studentAttendance.getCourseOffering().getFaculty().getLastName();
		}
		average = sum / studentsAttendance.size();
		model.addAttribute("studentsAttendance", studentsAttendance);
		model.addAttribute("average", average);
		model.addAttribute("faculty", faculty);

		return "attendanceReport";
	}
}
