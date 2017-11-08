package edu.mum.cs.projects.attendance.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.mum.cs.projects.attendance.Report.AttendanceReportPrint;
import edu.mum.cs.projects.attendance.domain.StudentAttendance;
import edu.mum.cs.projects.attendance.domain.entity.AcademicBlock;
import edu.mum.cs.projects.attendance.domain.entity.CourseOffering;
import edu.mum.cs.projects.attendance.domain.entity.Student;
import edu.mum.cs.projects.attendance.service.AttendanceService;
import edu.mum.cs.projects.attendance.service.CourseService;
import edu.mum.cs.projects.attendance.service.StudentService;
import edu.mum.cs.projects.attendance.util.DateUtil;
import edu.mum.cs.projects.attendance.util.IDNumberUtil;

@Controller
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class CourseController {
	@Autowired
	CourseService courseService;

	@Autowired
	AttendanceService attendanceService;

	@Autowired
	StudentService studentService;

	@RequestMapping(value = "/courseOffering/list/{beginDate}", method = RequestMethod.GET)
	public String getAllCourse(@PathVariable("beginDate") String beginDate, Model model) {

		List<CourseOffering> courseOfferings = courseService.getCourseOfferings(beginDate);
		model.addAttribute("courseOfferings", courseOfferings);

		return "courseOfferings";
	}

	@RequestMapping(value = "/courseOffering/getrecord/{cofferingid}", method = RequestMethod.GET)
	public String getAttendanceRecords(@PathVariable("cofferingid") long cofferingid, Model model) {

		CourseOffering coffering = courseService.getCourseOfferingbyID(cofferingid);
		AcademicBlock block = courseService.getAcademicBlock(DateUtil.convertDateToString(coffering.getStartDate()));
		coffering.setBlock(block);
		List<StudentAttendance> studentAttendance = attendanceService.retrieveStudentAttendanceRecords(coffering);
		model.addAttribute("studentAttendance", studentAttendance);
		model.addAttribute("block", block);

		return "attendanceListStudent";
	}

	@RequestMapping(value = "/courseOffering/getreport/{cofferingid}", method = RequestMethod.GET)
	public String getAttendanceRecordsReport(@PathVariable("cofferingid") long cofferingid, Model model) {

		CourseOffering coffering = courseService.getCourseOfferingbyID(cofferingid);
		AcademicBlock block = courseService.getAcademicBlock(DateUtil.convertDateToString(coffering.getStartDate()));
		coffering.setBlock(block);
		List<StudentAttendance> studentAttendance = attendanceService.retrieveStudentAttendanceRecords(coffering);
		/*
		 * model.addAttribute("studentAttendance", studentAttendance);
		 * model.addAttribute("block", block);
		 */

		model.addAttribute("attendancereportPrint", AttendanceReportPrint.ConvertToMap(studentAttendance));

		return "attendanceReportPrint";
	}

	@RequestMapping(value = "/courseOffering/student/{cofferingid}", method = RequestMethod.GET)
	public String getAttendanceRecordsStudent(@PathVariable("cofferingid") long cofferingid, Model model,
			Authentication authentication) {

		CourseOffering coffering = courseService.getCourseOfferingbyID(cofferingid);

		AcademicBlock block = courseService.getAcademicBlock(DateUtil.convertDateToString(coffering.getStartDate()));
		coffering.setBlock(block);

		String studentId = IDNumberUtil.convertToStudentId(Long.valueOf(authentication.getName()));

		Student student = studentService.getStudentsById(studentId);

		List<StudentAttendance> studentAttendance = attendanceService.retrieveStudentAttendanceRecords(coffering);

		if (studentAttendance == null) {
			return "redirect:/student/Courselist?attendance=none";
		}

		studentAttendance = studentAttendance.stream().filter(a -> a.getStudent().equals(student))
				.collect(Collectors.toList());

		model.addAttribute("studentAttendance", studentAttendance);
		model.addAttribute("block", block);

		return "studentCourseOfferingAttendance";
	}

	@RequestMapping(value = "/courseOffering/faculty/{cofferingid}", method = RequestMethod.GET)
	public String getAttendanceRecordsFaculty(@PathVariable("cofferingid") long cofferingid, Model model,
			Authentication authentication) {

		CourseOffering coffering = courseService.getCourseOfferingbyID(cofferingid);
		System.out.println("coffering.getStartDate() " + coffering.getStartDate());
		AcademicBlock block = courseService.getAcademicBlock(DateUtil.convertDateToString(coffering.getStartDate()));
		System.out.println("academic block " + block);
		coffering.setBlock(block);

		List<StudentAttendance> studentAttendance = attendanceService.retrieveStudentAttendanceRecords(coffering);

		System.out.println(studentAttendance);

		model.addAttribute("studentAttendance", studentAttendance);
		model.addAttribute("block", block);

		return "facultyCourseOfferingAttendance";
	}

	@RequestMapping(value = "/getallblocks", method = RequestMethod.GET)
	public String getAllAcadamicBlocks(Model model) {

		List<AcademicBlock> blocks = courseService.getAllAcademicBlock();
		model.addAttribute("blocks", blocks);

		return "academicblocks";
	}

}
