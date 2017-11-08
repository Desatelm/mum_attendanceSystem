package edu.mum.cs.projects.attendance.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.cs.projects.attendance.domain.entity.CustomUserDetails;
import edu.mum.cs.projects.attendance.domain.entity.Faculty;
import edu.mum.cs.projects.attendance.domain.entity.Role;
import edu.mum.cs.projects.attendance.domain.entity.Student;
import edu.mum.cs.projects.attendance.domain.entity.Users;
import edu.mum.cs.projects.attendance.repository.UsersRepository;

@Service
@Transactional
public class UserService {
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private StudentService studentService;
	@Autowired
	private FacultyService facultyService;
	@Autowired
	private UserService userService;

	public void createUser(Users user) {
		usersRepository.save(user);
	}

	public Users getUserByID(int userId) {
		return usersRepository.findById(userId);
	}

	public Users getUserByUserName(String username) throws UsernameNotFoundException {
		Optional<Users> optionalUsers = usersRepository.findByName(username);
		if (!optionalUsers.isPresent()) {
			return null;
		}
		return optionalUsers.map(Users::new).get();
	}

	public Users getUser(String username) throws UsernameNotFoundException {
		Optional<Users> optionalUsers = usersRepository.findByName(username);

		optionalUsers.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
		return optionalUsers.map(CustomUserDetails::new).get();
	}

	@Transactional
	public void creatUsers() {

		System.out.println("User Account for Students is creating.....");
		Role role1 = new Role();
		role1.setRoleId(1);
		role1.setRole("STUDENT");

		for (Student student : studentService.getAllStudents()) {// .getStudentsByEntry("2017-02-09
																	// 00:00:00"))
																	// {

			Users user = new Users();
			String temp = student.getStudentId();
			String userName = "";
			String[] str = temp.split("-");

			for (String st : str) {
				userName += st;
			}

			user.setEmail(student.getFirstName() + student.getLastName().substring(0, 1) + "@mum.edu");
			user.setStudentId(student.getStudentId());

			user.setName(userName.substring(3));
			user.setPassword(student.getLastName() + "123");
			user.setActive(1);
			user.setRoles(role1);
			userService.createUser(user);
		}

		System.out.println("User Account for Faculty is creating.....");
		Role role2 = new Role();
		role2.setRole("FACULTY");
		role2.setRoleId(2);
		for (Faculty faculty : facultyService.getAll()) {

			System.out.println("User Account for Faculty is creating.....");
			Users user = new Users();

			user.setName(faculty.getLastName() + faculty.getId());
			user.setFacultyId(faculty.getId());
			user.setEmail(faculty.getLastName() + "@mum.edu");
			user.setActive(0);
			user.setPassword(faculty.getLastName() + "123");
			user.setRoles(role2);
			userService.createUser(user);
		}
		System.out.println("User Account for Admin is creating.....");
		Role role3 = new Role();
		role3.setRole("ADMIN");
		role3.setRoleId(3);
		Users user = new Users();

		user.setName("Admin");
		user.setEmail("Admin" + "@mum.edu");
		user.setActive(0);
		user.setPassword("admin" + "123");
		user.setRoles(role3);
		userService.createUser(user);

		System.out.println("User Account for Staff is creating.....");
		Role role4 = new Role();
		role4.setRole("STAFF");
		role4.setRoleId(4);
		user = new Users();

		user.setName("Staff");
		user.setEmail("staff" + "@mum.edu");
		user.setActive(0);
		user.setPassword("staff" + "123");
		user.setRoles(role4);
		userService.createUser(user);

		System.out.println("All user Account are created.....");
	}

}
