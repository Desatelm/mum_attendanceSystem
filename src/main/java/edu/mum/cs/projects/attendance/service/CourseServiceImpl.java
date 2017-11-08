package edu.mum.cs.projects.attendance.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.joda.time.Months;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.mum.cs.projects.attendance.domain.ComproEntry;
import edu.mum.cs.projects.attendance.domain.entity.AcademicBlock;
import edu.mum.cs.projects.attendance.domain.entity.Course;
import edu.mum.cs.projects.attendance.domain.entity.CourseOffering;
import edu.mum.cs.projects.attendance.domain.entity.Enrollment;
import edu.mum.cs.projects.attendance.domain.entity.Faculty;
import edu.mum.cs.projects.attendance.repository.AcademicBlockRepository;
import edu.mum.cs.projects.attendance.repository.CourseOfferingRepository;
import edu.mum.cs.projects.attendance.repository.FacultyRepository;
import edu.mum.cs.projects.attendance.util.DateUtil;

/**
 * <h1>Maharishi University of Management<br/>
 * Computer Science Department</h1>
 * 
 * <p>
 * Service layer facade, hides away details of dataaccess layer from client.
 * </p>
 *
 * @author Payman Salek
 * 
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	CourseOfferingRepository courseOfferingRepository;

	@Autowired
	AcademicBlockRepository academicBlockRepository;
	@Autowired
	FacultyRepository facultyRepository;

	@Override
	public List<ComproEntry> getComproEntries(String startDate) {
		Course course = new Course("Entry");
		List<CourseOffering> offerings = courseOfferingRepository.findByCourse(course);

		return offerings.stream().map(offering -> new ComproEntry(offering))
				.filter(ce -> ce.getDate().isAfter(LocalDate.parse(startDate)))
				.sorted(Comparator.comparing(ComproEntry::getDate)).distinct().collect(Collectors.toList());
	}

	@Override
	public List<CourseOffering> getCourseOfferings(String blockStartDate) {
		Date date = DateUtil.convertStringToDate(blockStartDate);
		AcademicBlock block = getAcademicBlock(blockStartDate);

		List<CourseOffering> offerings = courseOfferingRepository.findByStartDate(date);

		return offerings.stream().filter(o -> o.isOnCampus() && o.isActive()).peek(o -> o.setBlock(block))
				.collect(Collectors.toList());
	}

	// -----------added
	@Override
	public CourseOffering getCourseOfferingbyID(long courseofferingId) {
		CourseOffering offering = courseOfferingRepository.findById(courseofferingId);
		return offering;
	}

	@Override
	public AcademicBlock getAcademicBlock(String blockBeginDate) {
		Date beginDate = DateUtil.convertStringToDate(blockBeginDate);
		return academicBlockRepository.findByBeginDate(beginDate);
	}

	@Override
	public List<Enrollment> getEnrollment(CourseOffering offering) {
		return offering.getEnrollments().stream()
				.filter(o -> Enrollment.Status.SIGNEDUP.toString().equalsIgnoreCase(o.getStatus()))
				.collect(Collectors.toList());
	}

	// to get all the acadamic blocks so that we can choose corse fofering for
	// each blocks
	@Override
	public List<AcademicBlock> getAllAcademicBlock() {
		return academicBlockRepository.findAll();
	}

	// to get all list of courseoffering for a facluty he/she taought for the
	// past six months
	@Override
	public List<CourseOffering> getCourseOfferingsPastSixMonths(Long facultyId) {

		Faculty faculty = facultyRepository.findById(facultyId);
		List<CourseOffering> offerings = courseOfferingRepository.findByFaculty(faculty);

		Predicate<CourseOffering> byLessThanSixMonths = course -> {
			Months months = Months.monthsBetween(new DateTime(course.getStartDate()), new DateTime());
			System.out.println("months: " + months.getMonths());
			return months.getMonths() <= 6;
		};

		return offerings.stream().filter(byLessThanSixMonths).collect(Collectors.toList());
	}

}
