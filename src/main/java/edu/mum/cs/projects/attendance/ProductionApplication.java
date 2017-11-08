package edu.mum.cs.projects.attendance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("production.properties")
public class ProductionApplication {
	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(ProductionApplication.class, args);

	}
}
