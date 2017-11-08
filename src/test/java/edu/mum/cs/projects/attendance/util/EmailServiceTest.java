package edu.mum.cs.projects.attendance.util;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import edu.mum.cs.projects.attendance.domain.Email;
import edu.mum.cs.projects.attendance.service.EmailService;
import edu.mum.cs.projects.attendance.service.EmailServiceImpl;

public class EmailServiceTest {

	@Test
	public void testSendsEmail() {
		EmailService emailService = new EmailServiceImpl();
		Email email = mock(Email.class);

		emailService.sendEmail(email);

		verify(email, times(1)).getBody();

	}

}
