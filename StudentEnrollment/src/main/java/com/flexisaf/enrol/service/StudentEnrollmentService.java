package com.flexisaf.enrol.service;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.http.ResponseEntity;

import com.flexisaf.enrol.model.Student;
import com.flexisaf.enrol.model.StudentSearch;
import com.flexisaf.enrol.model.dto.StudentDto;

public interface StudentEnrollmentService {

	ResponseEntity<StudentDto> getStudent(long id);

	ResponseEntity<List<StudentDto>> getAllStudent();

	ResponseEntity<String> deleteStudent(long id);

	ResponseEntity<Long> registerStudent(StudentDto studentDto);

	ResponseEntity<Boolean> updateStudent(Student student);
	
	ResponseEntity<List<StudentDto>> SearchStudent(StudentSearch studentSearch, Pageable pageable);

}