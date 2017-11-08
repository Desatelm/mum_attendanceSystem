package edu.mum.cs.projects.attendance.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import org.springframework.web.bind.annotation.RequestParam;

import edu.mum.cs.projects.attendance.Report.AttendanceReportPrint;
import edu.mum.cs.projects.attendance.domain.StudentAttendance;
import edu.mum.cs.projects.attendance.domain.entity.AcademicBlock;
import edu.mum.cs.projects.attendance.domain.entity.BarcodeRecord;
import edu.mum.cs.projects.attendance.domain.entity.CourseOffering;
import edu.mum.cs.projects.attendance.domain.entity.Student;
import edu.mum.cs.projects.attendance.service.AttendanceService;
import edu.mum.cs.projects.attendance.service.BarcodeService;
import edu.mum.cs.projects.attendance.service.CourseService;
import edu.mum.cs.projects.attendance.service.EnrollmentService;
import edu.mum.cs.projects.attendance.service.StudentService;
import edu.mum.cs.projects.attendance.util.DateUtil;

@Controller
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class StudentAttendanceController {

	@Autowired
	CourseService courseService;

	@Autowired
	AttendanceService attendanceService;

	@Autowired
	StudentService studentService;

	@Autowired
	EnrollmentService enrollmentService;

	@Autowired
	BarcodeService barcodeService;

	@RequestMapping(value = "/my/courselist")
	public String getStudentCourseList(String studentid, Model model, Authentication authentication) {

		model.addAttribute("enrolledCourses",
				enrollmentService.getEnrolledCoursesByStudentId(authentication.getName()));

		return "studentCourseList";
	}

	@RequestMapping(value = "/my/attendance")
	public String getStudentAttendanceforAcourse(String offeringid, String studentid, Model model) {

		return "studentAttendance";
	}

	@RequestMapping(value = "/attendance/student/{studentId}/{cofferingid}", method = RequestMethod.GET)
	public String getAttendanceRecordsStudent(@PathVariable("studentId") String studentId,
			@PathVariable("cofferingid") long cofferingid, Model model, Authentication authentication) {

		CourseOffering coffering = courseService.getCourseOfferingbyID(cofferingid);

		AcademicBlock block = courseService.getAcademicBlock(DateUtil.convertDateToString(coffering.getStartDate()));
		coffering.setBlock(block);

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

	@RequestMapping(value = "/attendance/studentPDFPrint/{studentId}/{cofferingid}", method = RequestMethod.GET)
	public String getAttendanceRecordsStudentPDFPrint(@PathVariable("studentId") String studentId,
			@PathVariable("cofferingid") long cofferingid, Model model, Authentication authentication) {

		CourseOffering coffering = courseService.getCourseOfferingbyID(cofferingid);

		AcademicBlock block = courseService.getAcademicBlock(DateUtil.convertDateToString(coffering.getStartDate()));
		coffering.setBlock(block);

		Student student = studentService.getStudentsById(studentId);

		List<StudentAttendance> studentAttendance = attendanceService.retrieveStudentAttendanceRecords(coffering);

		if (studentAttendance == null) {
			return "redirect:/student/Courselist?attendance=none";
		}

		studentAttendance = studentAttendance.stream().filter(a -> a.getStudent().equals(student))
				.collect(Collectors.toList());
		model.addAttribute("attendancereportPrint", AttendanceReportPrint.ConvertToMap(studentAttendance));

		return "attendanceReportPrint";
	}

	@RequestMapping(value = "/attendance/update", method = RequestMethod.GET)
	public String getBarcodeRecordsListByDate(@RequestParam("atendanceType") String atendanceType,
			@RequestParam("offeringId") String offeringId, @RequestParam("recordDate") String recordDate,
			@RequestParam("studentId") String studentId, Model model) {
		LocalDate localDate = LocalDate.parse(recordDate);
		String redirectUrl;
		if (atendanceType.equals("one")) {
			redirectUrl = "/attendance/student/" + studentId + "/" + offeringId;
		} else {
			redirectUrl = "/courseOffering/getrecord/" + offeringId;
		}

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		Date date = new Date();

		try {
			date = format.parse(recordDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<BarcodeRecord> barcodeRecords = barcodeService.getBarcodeRecordsListByDateAndStudentID(date, studentId);
		if (barcodeRecords.size() == 0) {
			System.out.println("----------------------------barcode recorde created");

			barcodeService.addBarcodeRecordList(localDate, studentId);
		} else {
			System.out.println("---------------------------- bardcode deleted");
			System.out.println(barcodeRecords);
			barcodeService.deleteBarcodeRecordByBarcodeID(barcodeRecords.get(0).getId());
		}
		/*
		 * model.addAttribute("barcodeRecords", barcodeRecords);
		 */
		return "redirect:" + redirectUrl;
	}

}
