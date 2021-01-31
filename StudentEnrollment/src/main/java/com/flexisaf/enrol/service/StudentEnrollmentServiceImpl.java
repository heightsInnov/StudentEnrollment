package com.flexisaf.enrol.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flexisaf.enrol.model.Student;
import com.flexisaf.enrol.model.StudentSearch;
import com.flexisaf.enrol.model.dto.StudentDto;
import com.flexisaf.enrol.repository.StudentEnrollmentRepository;
import com.flexisaf.enrol.utility.ObjectMapper;

@Service("StudentEnrollmentService")
public class StudentEnrollmentServiceImpl implements StudentEnrollmentService {

	@Autowired
	StudentEnrollmentRepository studentEnrollmentRepository;

	@Override
	public ResponseEntity<StudentDto> getStudent(long id) {
		try {

			Optional<Student> students = studentEnrollmentRepository.findById(id);

			if (students.isPresent()) {
				return ResponseEntity.status(HttpStatus.OK).body(ObjectMapper.convertToDto(students.get()));
			} else
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@Override
	public ResponseEntity<List<StudentDto>> getAllStudent() {
		try {

			List<StudentDto> studentss = new ArrayList<StudentDto>();
			
			studentEnrollmentRepository.findAll().forEach(x-> {
				studentss.add(ObjectMapper.convertToDto(x));
			});
			
			if (!studentss.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(studentss);
			}else
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ArrayList<>());

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@Override
	public ResponseEntity<String> deleteStudent(long id) {
		try {
			Optional<Student> students = studentEnrollmentRepository.findById(id);

			if (students.isPresent()) {
				studentEnrollmentRepository.deleteById(id);
				return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted");
				
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No student found with student id: "+id);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@Override
	public ResponseEntity<Long> registerStudent(StudentDto student) {
		try {
			long id = studentEnrollmentRepository.save(ObjectMapper.convertToModel(student)).getId();
			student.setMatricnumber("FLEXISAF/" + String.valueOf(id));
			studentEnrollmentRepository.flush();
			return ResponseEntity.status(HttpStatus.OK).body(id);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@Override
	public ResponseEntity<Boolean> updateStudent(Student student) {
		return ResponseEntity.status(HttpStatus.OK).body(
				studentEnrollmentRepository.updateStudent(student.getFirstname(), student.getLastname(), student.getOthername(),
				student.getGender(), student.getDateofbirth(), student.getDepartment()));
	}
	
	public ResponseEntity<List<StudentDto>> SearchStudent(StudentSearch student, Pageable pageable) {
		List<StudentDto> students = new ArrayList<StudentDto>();
		studentEnrollmentRepository
		.searchStudent(student.getFirstname(), student.getLastname(), student.getOthername(), 
	    		student.getGender(), student.getDepartment(), student.getFullname(), student.getStartDate(), 
	    		student.getEndDate(), pageable).subList(0, 50).forEach(x -> {
	    			students.add(ObjectMapper.convertToDto(x));
	    		});
		if (!students.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(students);
		}else
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ArrayList<>());
	}
}
