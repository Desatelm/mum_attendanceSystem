package edu.mum.cs.projects.attendance.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	public void createUser(Users user){
		System.out.println("user" + user.getPassword());
		usersRepository.save(user);
	}
	
	public Users getUser(String studentId){
		return usersRepository.findByStudentId(studentId);
		
	}	
	
	@Transactional
	public void creatUser(){         
		Set<Role> roles = new HashSet<>();		
		for(Student student: studentService.getAllStudents()){//.getStudentsByEntry("2017-02-09 00:00:00")){
			
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
			user.setEmail(student.getFirstName() + student.getLastName().substring(0,1) + "@mum.edu");
			user.setStudentId(student.getStudentId());
			user.setName(userName);
			user.setPassword(student.getLastName()+"123");			
			user.setActive(1);
			
			user.setRoles(roles);			
			createUser(user);
			//System.out.println(userService.getUser(user.getStudentId()).getName());
			}			
		
	}

}
