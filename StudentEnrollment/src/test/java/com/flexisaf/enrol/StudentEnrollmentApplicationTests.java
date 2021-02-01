package com.flexisaf.enrol;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.flexisaf.enrol.model.Student;
import com.flexisaf.enrol.model.dto.StudentDto;
import com.flexisaf.enrol.repository.StudentEnrollmentRepository;
import com.flexisaf.enrol.utility.ObjectMapper;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
class StudentEnrollmentApplicationTests {

	@Autowired
	StudentEnrollmentRepository repo;
	
	Long id = null;
	
	@Test
	void contextLoads() {
	}
	
	@Test
    @Rollback(false)
    @Order(1)
    public void registerStudent() {
		StudentDto student = new StudentDto();
		student.setFirstname("Adesola");
		student.setLastname("Erinoso");
		student.setOthername("Samuel");
		student.setDateofbirth("03/09/1985");
		student.setGender("M");
		student.setDepartment("DEVELOPMENT");
	     
		
		Student saved = repo.save(ObjectMapper.convertToModel(student));
		id = saved.getId();
		assertThat(saved.getId()).isGreaterThan(0);
    }
     
    @Test
    @Order(2)
    public void getStudentById() {
    	Student student = repo.findByFirstname("Adesola");   
        assertThat(student.getFirstname()).isEqualTo("Adesola");
    }
     
    @Test
    @Order(3)
    public void getAllStudent() {
    	List<Student> student = (List<Student>) repo.findAll();
        assertThat(student).size().isGreaterThan(0);
    }
     
    @Test
    @Rollback(false)
    @Order(4)
    public void updateStudent() {
    	Student student = repo.findByFirstname("Adesola");
        student.setLastname("Malik");
         
        repo.save(student);
         
        Student updatedStudent = repo.findByFirstname("Adesola");
         
        assertThat(updatedStudent.getLastname()).isEqualTo("Malik");
    }
     
    @Test
    @Rollback(false)
    @Order(5)
    public void deleteStudent() {
    	Student student = repo.findByFirstname("Adesola");
        
        repo.deleteById(student.getId());
         
        Student  deletedStudent = repo.findByFirstname("Adesola");
         
        assertThat(deletedStudent).isNull(); 
    }

}
