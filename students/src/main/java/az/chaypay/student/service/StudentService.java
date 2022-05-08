package az.chaypay.student.service;


import az.chaypay.student.repository.dao.Student;

import java.util.List;

public interface StudentService {
    List<Student> getStudents();
}
