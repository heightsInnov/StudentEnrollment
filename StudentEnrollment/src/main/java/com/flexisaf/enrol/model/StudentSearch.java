package com.flexisaf.enrol.model;

import java.time.LocalDate;

public class StudentSearch {

	private String firstname;

	private String lastname;

	private String othername;

	private String gender;
	
	private String dateofbirth;

	private String department;
	
	private String fullname;
	
	private LocalDate startDate;
	
	private LocalDate endDate;

	
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getOthername() {
		return othername;
	}

	public void setOthername(String othername) {
		this.othername = othername;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "StudentSearch [firstname=" + firstname + ", lastname=" + lastname + ", othername=" + othername
				+ ", gender=" + gender + ", dateofbirth=" + dateofbirth + ", department=" + department + ", fullname="
				+ fullname + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
	
}
