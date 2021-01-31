package com.flexisaf.enrol.utility;

import java.time.LocalDate;

import com.flexisaf.enrol.model.Student;
import com.flexisaf.enrol.model.dto.StudentDto;

public class ObjectMapper {

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
		return studentDto;
	}

	public static Student convertToModel(StudentDto student) {
		Student studentDto = new Student();
		studentDto.setId(Long.valueOf(student.getStudentId()));
		studentDto.setFirstname(student.getFirstname());
		studentDto.setLastname(student.getLastname());
		studentDto.setOthername(student.getOthername());
		studentDto.setGender(student.getGender());
		studentDto.setDepartment(student.getDepartment());
		studentDto.setDateofbirth(LocalDate.parse(student.getDateofbirth()));
		studentDto.setMatricnumber(student.getMatricnumber());
		return studentDto;
	}
}
