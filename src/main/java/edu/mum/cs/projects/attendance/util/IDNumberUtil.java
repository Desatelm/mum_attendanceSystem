package edu.mum.cs.projects.attendance.util;

public class IDNumberUtil {

	public static String convertToStudentId(long id) {
		// final output format 000-98-0201
		// initialize the id to always be used to extract, the parts 1,2,3, the
		// finalString,
		// a copy of the id
		long lastFourDigits, middleTwoDigits, firstThreeDigits;
		long idCopy = id;
		String answer = "";

		// extract last four digits -> part1, update the idCopy
		lastFourDigits = idCopy % 10000;
		idCopy = idCopy / 10000;

		// extract middle two digits -> part2 , update the idCopy
		middleTwoDigits = idCopy % 100;
		idCopy = idCopy / 100;

		// extract first three digits -> part3, use the idCopy directly
		firstThreeDigits = idCopy;

		// concantenate the parts 1,2,3 adding the correct paddings and zeros
		answer = String.format("%03d-%02d-%04d", firstThreeDigits, middleTwoDigits, lastFourDigits);
		// return the final string
		return answer;
	}

	public static String convertToStudentId(String id) {
		// final output format 000-98-0201

		// does not handle other strings

		// check if its not already in the required format and return
		if (!id.matches("\\d+")) {
			return id;
		}

		// a copy of the id is initialized, and the overloaded long method is
		// called
		long idCopy = Long.parseLong(id);
		return convertToStudentId(idCopy);
	}

}
