package az.chaypay.student.controller;

import az.chaypay.student.repository.dao.Student;
import az.chaypay.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService service;

    @GetMapping
    ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.ok(service.getStudents());
    }
}
