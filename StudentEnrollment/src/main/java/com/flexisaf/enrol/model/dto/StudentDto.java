package com.flexisaf.enrol.model.dto;

public class StudentDto {

	private String studentId;

	private String firstname;

	private String lastname;

	private String othername;

	private String gender;

	private String dateofbirth;

	private String department;

	private String matricnumber;

	private String studentemail;

	private String createdat;

	public String getStudentemail() {
		return studentemail;
	}

	public void setStudentemail(String studentemail) {
		this.studentemail = studentemail;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
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

	public String getMatricnumber() {
		return matricnumber;
	}

	public void setMatricnumber(String matricnumber) {
		this.matricnumber = matricnumber;
	}

	public String getCreatedat() {
		return createdat;
	}

	public void setCreatedat(String createdat) {
		this.createdat = createdat;
	}

	@Override
	public String toString() {
		return "StudentDto [studentId=" + studentId + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", othername=" + othername + ", gender=" + gender + ", dateofbirth=" + dateofbirth + ", department="
				+ department + ", matricnumber=" + matricnumber + ", createdat=" + createdat + "]";
	}
	
	
}
