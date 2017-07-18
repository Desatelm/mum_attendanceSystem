package edu.mum.cs.projects.attendance.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.cs.projects.attendance.domain.ComproEntry;
import edu.mum.cs.projects.attendance.domain.StudentAttendance;
import edu.mum.cs.projects.attendance.domain.entity.AcademicBlock;
import edu.mum.cs.projects.attendance.domain.entity.BarcodeRecord;
import edu.mum.cs.projects.attendance.domain.entity.CourseOffering;
import edu.mum.cs.projects.attendance.domain.entity.Enrollment;
import edu.mum.cs.projects.attendance.domain.entity.Role;
import edu.mum.cs.projects.attendance.domain.entity.Session;
import edu.mum.cs.projects.attendance.domain.entity.Student;
import edu.mum.cs.projects.attendance.domain.entity.Timeslot;
import edu.mum.cs.projects.attendance.domain.entity.Users;
import edu.mum.cs.projects.attendance.ooxml.SpreadsheetWriterDAO;
import edu.mum.cs.projects.attendance.repository.BarcodeRecordRepository;
import edu.mum.cs.projects.attendance.util.DateUtil;

/**
 * <h1>Maharishi University of Management<br/>
 * Computer Science Department</h1>
 * 
 * <p>
 * Service layer facade, hides away details of business logic and dataaccess
 * from client.
 * </p>
 *
 * @author Payman Salek
 * 
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	private EmailService emailService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private StudentService studentService;

	@Autowired
	BarcodeRecordRepository barcodeRecordRepository;
	
	@Autowired
	private UserService userService;

	@Override
	public void countAttendancePerDay() {
		List<BarcodeRecord> records = new LinkedList<>();
		barcodeRecordRepository.findAll().forEach(br -> records.add(br));

		Map<LocalDate, Long> dateMap = records.stream().filter(r -> r.getTimeslot().getId().equals("AM"))
				.map(r -> r.getDate()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		dateMap.entrySet().stream().sorted(Comparator.comparing(Entry::getKey))
				.forEach(e -> System.out.println(e.getKey().toString() + "," + e.getValue()));
	}

	@Override
	public void createAttendanceReportForEntries(String startDate) {
		List<ComproEntry> entries = courseService.getComproEntries(startDate);
		for (ComproEntry entry : entries) {
			System.out.println("Attendance report for: " + entry);
			createAttendanceReportForEntry(entry.getDate().toString());
		}
	}

	@Override
	public void createAttendanceReportForEntry(String entryDate) {
		List<Student> students = studentService.getStudentsByEntry(entryDate);
		
		
		Date beginDate = new Date();
		beginDate.setTime(0);
		List<BarcodeRecord> records = barcodeRecordRepository.findByDateBetween(beginDate, new Date());

		records = records.stream().filter(b -> b.getLocation().equals("DB")).filter(b -> b.getTimeslot().equals("AM"))
				.sorted(Comparator.comparing(BarcodeRecord::getDate)).distinct().collect(Collectors.toList());

		List<LocalDate> dates = records.stream().map(r -> r.getDate()).collect(Collectors.toList());

		List<Session> sessions = sessionsForDateRange(LocalDate.parse(entryDate), LocalDate.now(), dates);

		students.stream().map(s -> new StudentAttendance(s, null)).map(populateAttendanceArray(records, sessions))
				.peek(System.out::println).collect(Collectors.toList());
	}

	private List<Session> sessionsForDateRange(LocalDate startDate, LocalDate endDate, List<LocalDate> dates) {
		List<Session> sessions = new LinkedList<>();

		endDate = endDate.plusDays(1); // inclusive range
		startDate = startDate.plusDays(7); // Allow seven days after entry date
											// for learning TM

		Timeslot timeslot = new Timeslot();
		timeslot.setId("AM");

		for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
			if (dates.contains(date)) {
				Session s = new Session();
				s.setDate(date);
				s.setTimeslot(timeslot);
				sessions.add(s);
			}
		}

		return sessions;
	}

	@Override
	public List<StudentAttendance> retrieveStudentAttendanceRecords(CourseOffering courseOffering) {
		boolean flag = true;
		List<Enrollment> enrollments = courseService.getEnrollment(courseOffering);
		System.out.println();
		if (null == enrollments || enrollments.isEmpty()) {
			return null;
		} 
        Set<Role> roles = new HashSet<Role>();		
		
		
		for(Enrollment student: enrollments){
			
			Role role =  new Role();
			role.setRole("Student");
			roles.add(role);
			Users user = new Users();
			
			user.setStudentId(student.getStudent().getStudentId());
			user.setName(student.getStudent().getStudentId());
			user.setPassword(student.getStudent().getLastName()+"123");			
			user.setActive(1);
			
			user.setRoles(roles);			
			userService.createUser(user);
			//System.out.println(userService.getUser(user.getStudentId()).getName());
			}
		
		AcademicBlock block = courseService
				.getAcademicBlock(DateUtil.convertDateToString(courseOffering.getStartDate()));

		Date beginDate = DateUtil.convertLocalDateToDate(block.getBeginDate());
		Date endDate = DateUtil.convertLocalDateToDate(block.getEndDate());
		List<BarcodeRecord> barcodeRecords = barcodeRecordRepository.findByDateBetween(beginDate, endDate);

		System.out.println("\nCreating attendance report for: " + courseOffering.getCourse() + " by "
				+ courseOffering.getFaculty());

		
		List<StudentAttendance> studentAttendanceRecords = enrollments.stream()
				.map(e -> new StudentAttendance(e.getStudent(), e.getOffering()))
				.map(populateAttendanceArray(barcodeRecords, block.getSessions())).peek(System.out::println)
				.collect(Collectors.toList());
		
		OptionalDouble average = studentAttendanceRecords.stream().mapToDouble(sa -> sa.getMeditaionPercentage())
				.average();
		if (average.isPresent()) {
			System.out.printf("Average group meditation participation for this class is: %5.1f%s\n",
					average.getAsDouble(), "%");
		}

		return studentAttendanceRecords;
	}

	private Function<StudentAttendance, StudentAttendance> populateAttendanceArray(List<BarcodeRecord> barcodeRecords,
			List<Session> sessions) {
		return sa -> {
			List<Boolean> attendance = new ArrayList<Boolean>(sessions.size());

			sa.setAttendance(attendance);

			boolean noBarcode = (null == sa.getStudent().getBarcode());

			for (Session session : sessions) {
				if (noBarcode) {
					attendance.add(false);
				} else {
					attendance.add(barcodeRecords.stream()
							.filter(br -> br.getBarcode().equals(sa.getStudent().getBarcode()))
							.filter(br -> br.getDate().equals(session.getDate()))
							.filter(br -> br.getTimeslot().equals(session.getTimeslot())).findFirst().isPresent());
				}
			}

			return sa;
		};
	}

	@Override
	public List<StudentAttendance> createAttendanceReportForOffering(CourseOffering courseOffering) {
		List<StudentAttendance> studentAttendanceList = retrieveStudentAttendanceRecords(courseOffering);
		SpreadsheetWriterDAO.createAttendanceReportSpreadsheet(studentAttendanceList);
		return studentAttendanceList;
	}
	@Override
	@Transactional
	public void creatUser(){
         Set<Role> roles = new HashSet<Role>();
         Users  user = userService.getUser("000-98-0196");         
			Role role =  new Role();
			role.setRole("STAFF");
			Role role1 =  new Role();
			//role1.setRole("Student");
			roles.add(role);
			//roles.add(role1);
			user.setRoles(roles);
			studentService.getStudentById(user.getStudentId());
		/*Set<Role> roless = userService.getUser("000-98-0091").getRoles();
		for(Role role: roless){
			System.out.println(role.getRole());
		}*/
		
		/*Set<Role> roles = new HashSet<Role>();
		for(Student student: studentService.getStudentsByEntry("2017-02-09 00:00:00")){
			
			Role role =  new Role();
			role.setRole("Student");
			roles.add(role);
			Users user = new Users();
			String temp = student.getStudentId();
			String userName = "";
			String[] str = temp.split("-");
			for (String st: str){
				userName += st;
			}
			System.out.println(userName.substring(3));
			user.setStudentId(student.getStudentId());
			user.setName(userName.substring(3));
			user.setPassword(student.getLastName()+"123");			
			user.setActive(1);
			user.setEmail(student.getFirstName() + student.getLastName().substring(0,1) + "@mum.edu");
			user.setRoles(roles);			
			userService.createUser(user);
			System.out.println(userService.getUser(user.getStudentId()).getName());
			}		*/	
		
	}

	@Override
	@Transactional
	public List<StudentAttendance> createAttendanceReportForBlock(String blockStartDate) {
	    			
		
		List<StudentAttendance> errorRecords = new ArrayList<>();
		for (CourseOffering offering : courseService.getCourseOfferings(blockStartDate)) {
			errorRecords.addAll(createAttendanceReportForOffering(offering));
		}

		return errorRecords;
	}

	@Override
	public void emailReportToStudentsForBlock(String blockStartDate) {
		for (CourseOffering offering : courseService.getCourseOfferings(blockStartDate)) {
			emailReportToStudentsForOffering(offering);
		}
	}

	@Override
	public void emailReportToStudentsForOffering(CourseOffering courseOffering) {
		List<StudentAttendance> studentAttendanceList = retrieveStudentAttendanceRecords(courseOffering);
		emailService.emailAttendanceReportToStudents(studentAttendanceList);
	}

}
