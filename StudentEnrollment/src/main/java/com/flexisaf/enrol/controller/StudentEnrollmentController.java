package com.flexisaf.enrol.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.flexisaf.enrol.model.Student;
import com.flexisaf.enrol.model.StudentSearch;
import com.flexisaf.enrol.model.dto.StudentDto;
import com.flexisaf.enrol.service.StudentEnrollmentService;

@CrossOrigin(origins = "http://localhost:9090")
@RestController
@RequestMapping("/api")
public class StudentEnrollmentController {

	@Autowired
	StudentEnrollmentService studentEnrollmentService;

	@GetMapping("/get-student/{id}")
	public @ResponseBody ResponseEntity<StudentDto> getStudent(@PathVariable(value = "id") long id) {
		return studentEnrollmentService.getStudent(id);
	}

	@GetMapping("/get-student")
	public @ResponseBody ResponseEntity<List<StudentDto>> getAllStudent() {
		return studentEnrollmentService.getAllStudent();
	}

	@PostMapping("/register-student")
	public @ResponseBody ResponseEntity<Long> register(@RequestBody StudentDto student) {
		return studentEnrollmentService.registerStudent(student);
	}

	@PutMapping("/update-student/{id}")
	public @ResponseBody ResponseEntity<Boolean> update(@PathVariable("id") long id, @RequestBody Student student) {
		student.setId(id);
		return studentEnrollmentService.updateStudent(student);
	}

	@DeleteMapping("/delete-student/{id}")
	public @ResponseBody ResponseEntity<String> delete(@PathVariable("id") long id) {
		return studentEnrollmentService.deleteStudent(id);
	}

	@GetMapping("/search-student")
	public ResponseEntity<List<StudentDto>> getEntry(@RequestParam(value = "firstname", required = false) String firstname,
			@RequestParam(value = "lastname", required = false) String lastname,
			@RequestParam(value = "othername", required = false) String othername,
			@RequestParam(value = "gender", required = false) String gender,
			@RequestParam(value = "department", required = false) String department,
			@RequestParam(value = "fullname", required = false) String fullname,
			@RequestParam(value = "from", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate from,
			@RequestParam(value = "to", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate to,
			Pageable pageable) {

		StudentSearch studentSearch = new StudentSearch();
		studentSearch.setFirstname(firstname);
		studentSearch.setLastname(lastname);
		studentSearch.setOthername(othername);
		studentSearch.setGender(gender);
		studentSearch.setDepartment(department);
		studentSearch.setFullname(fullname);
		studentSearch.setStartDate(from);
		studentSearch.setEndDate(to);

		return studentEnrollmentService.SearchStudent(studentSearch, pageable);
	}
}
