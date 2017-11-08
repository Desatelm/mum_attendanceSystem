package edu.mum.cs.projects.attendance.Report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.mum.cs.projects.attendance.domain.StudentAttendance;

public class AttendanceReportPrint {

	public static List<Map<String, ?>> ConvertToMap(List<StudentAttendance> stList) {

		List<Map<String, ?>> attendanceList = new ArrayList<Map<String, ?>>();
		for (StudentAttendance std : stList) {

			int index = 0;
			Map<String, Object> mp = new HashMap<String, Object>();
			mp.put("id", std.getStudent().getStudentId());
			mp.put("fname", std.getStudent().getFirstName());
			mp.put("CourseID", std.getCourseOffering().getCourse().getNumber());
			mp.put("OFferingID", std.getCourseOffering().getId());
			mp.put("CourseName", std.getCourseOffering().getCourse().getName());

			for (boolean att : std.getAttendance()) {
				index++;
				if (att)
					mp.put("attendance" + index, 1);
				else
					mp.put("attendance" + index, 0);

			}

			mp.put("percentage", std.getMeditaionPercentage());
			mp.put("extragrade", std.getMeditationExtraGrade());
			attendanceList.add(mp);
		}

		return attendanceList;
	}

}