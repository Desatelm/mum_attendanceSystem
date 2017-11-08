package edu.mum.cs.projects.attendance;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import edu.mum.cs.projects.attendance.service.UserService;

//@SpringBootApplication
public class CreateUsers {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(CreateUsers.class, args);

		UserService service = context.getBean(UserService.class);
		service.creatUsers();
		System.exit(0);
	}

}
