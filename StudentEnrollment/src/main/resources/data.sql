DROP TABLE IF EXISTS Student;

CREATE TABLE Student (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  firstname VARCHAR(250) NOT NULL,
  lastname VARCHAR(250) NOT NULL,
  othername VARCHAR(250) DEFAULT NULL,
  gender VARCHAR(1) NOT NULL,
  dateofbirth DATE DEFAULT NULL,
  department VARCHAR(250) DEFAULT NULL,
  matricnumber VARCHAR(250) DEFAULT NULL,
  createdat DATE DEFAULT NULL,
  studentemail VARCHAR(100) DEFAULT NULL
);

INSERT INTO Student (id, firstname, lastname, gender, department, matricnumber) VALUES
  (1, 'Aliko', 'Dangote', 'M', 'Technology', 'FLEXISAF/001');