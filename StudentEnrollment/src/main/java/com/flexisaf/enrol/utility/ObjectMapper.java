package com.flexisaf.enrol.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.flexisaf.enrol.model.Student;
import com.flexisaf.enrol.model.dto.StudentDto;

public class ObjectMapper {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
	
	public static StudentDto convertToDto(Student student) {
		StudentDto studentDto = new StudentDto();
		if (Long.valueOf(student.getId()) != null) {
			studentDto.setStudentId(String.valueOf(student.getId()));
		}
		studentDto.setFirstname(student.getFirstname());
		studentDto.setLastname(student.getLastname());
		studentDto.setOthername(student.getOthername());
		studentDto.setGender(student.getGender());
		studentDto.setDepartment(student.getDepartment());
		studentDto.setDateofbirth(student.getDateofbirth().toString());
		studentDto.setMatricnumber(student.getMatricnumber());
		if (student.getCreatedat() != null)
			studentDto.setCreatedat(student.getCreatedat().toString());
		return studentDto;
	}

	public static Student convertToModel(StudentDto student) {
		
		Student studentDto = new Student();
		if (student.getStudentId() != null) {
			studentDto.setId(Long.valueOf(student.getStudentId()));
		}
		studentDto.setFirstname(student.getFirstname());
		studentDto.setLastname(student.getLastname());
		studentDto.setOthername(student.getOthername());
		studentDto.setGender(student.getGender());
		studentDto.setDepartment(student.getDepartment());
		studentDto.setDateofbirth(LocalDate.parse(student.getDateofbirth(), formatter));
		studentDto.setMatricnumber(student.getMatricnumber());
		return studentDto;
	}
}
