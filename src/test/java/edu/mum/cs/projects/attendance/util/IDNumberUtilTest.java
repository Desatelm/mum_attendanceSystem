package edu.mum.cs.projects.attendance.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class IDNumberUtilTest {

	@Test
	public void testConvertToStudentIdString() {
		String expected = "000-98-0201";
		long studentId = 980201;
		String actual = IDNumberUtil.convertToStudentId(studentId);
		System.out.println(actual);
		assertEquals(expected, actual);
	}

	@Test
	public void testConvertToStudentIdStringAnother() {
		String expected = "010-98-0201";
		long studentId = 10980201;
		String actual = IDNumberUtil.convertToStudentId(studentId);
		System.out.println(actual);
		assertEquals(expected, actual);
	}

	@Test
	public void testConvertToStudentIdStringString() {
		String expected = "000-98-0201";
		String studentId = "980201";
		String actual = IDNumberUtil.convertToStudentId(studentId);
		System.out.println(actual);
		assertEquals(expected, actual);
	}

	@Test
	public void testConvertToStudentIdStringAnotherString() {
		String expected = "010-98-0201";
		String studentId = "010-98-0201";
		String actual = IDNumberUtil.convertToStudentId(studentId);
		System.out.println(actual);
		assertEquals(expected, actual);
	}
}
