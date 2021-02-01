package com.flexisaf.enrol.repository;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.flexisaf.enrol.model.Student;

@Transactional
public interface StudentEnrollmentRepository extends JpaRepository<Student, Long> {

	@Modifying
	@Query("update Student u set u.firstname = ?1, u.lastname = ?2, u.othername = ?3, u.gender = ?4,  u.dateofbirth = ?5, u.department = ?6 where u.id = ?7")
	int updateStudent(String firstname, String lastname, String othername, String gender, LocalDate dob,
			String department, long id);

	@Query("select m "
			+ " from Student m where " 
			+ "(?1 is null or upper(m.firstname) like concat('%', upper(?1), '%')) "
			+ "and (?2 is null or upper(m.lastname) like concat('%', upper(?2), '%')) "
			+ "and (?3 is null or upper(m.othername) like concat('%', upper(?3), '%')) "
			+ "and (?4 is null or upper(m.gender) like concat('%', upper(?4), '%')) "
			+ "and (?5 is null or upper(m.department) like concat('%', upper(?5), '%')) "
			+ "and (?6 is null or concat(upper(m.firstname), upper(m.lastname), upper(m.othername)) like concat('%', upper(?6), '%')) "
			+ "and (?7 is null or m.createdat >= ?7) " 
			+ "and (?8 is null or m.createdat <= ?8)")
	List<Student> searchStudent(String firstname, String lastname, String othername, String gender, String department, String fullname,
			final LocalDate from, final LocalDate to, final Pageable pageable);
	
	Student findByFirstname(String firstname);
}
