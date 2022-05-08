package az.chaypay.student.service.impl;


import az.chaypay.teacher.api.TeacherService;
import az.chaypay.student.repository.StudentRepository;
import az.chaypay.student.repository.dao.Student;
import az.chaypay.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository repository;
//    private final TeacherRepository teacherRepository;
    private final TeacherService teacherService;
    @Override
    public List<Student> getStudents() {
//        System.out.println(teacherRepository.findAll());
        System.out.println(teacherService.getTeachers());
        return repository.findAll();
    }
}
