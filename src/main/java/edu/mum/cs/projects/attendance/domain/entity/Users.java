package edu.mum.cs.projects.attendance.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;
	@Column(name = "email")

	private String email;
	@Column(name = "password")
	private String password;

	@Column(name = "name", unique = true)
	private String name;
	@Column(name = "Student_Id")
	private String studentId;
	@Column(name = "Faculty_Id", columnDefinition = "int")
	private Long facultyId;
	@Column(name = "active")
	private int active;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Role roles;

	public Long getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(Long facultyId) {
		this.facultyId = facultyId;
	}

	public Users() {
	}

	public Users(Users users) {
		this.active = users.getActive();
		this.email = users.getEmail();
		this.roles = users.getRoles();
		this.name = users.getName();
		this.studentId = users.getStudentId();
		this.id = users.getId();
		this.password = users.getPassword();
		this.facultyId = users.getFacultyId();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String lastName) {
		this.studentId = lastName;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Role getRoles() {
		return roles;
	}

	public void setRoles(Role roles) {
		this.roles = roles;
	}

}
