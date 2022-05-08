package az.chaypay.teacher.controller;

import az.chaypay.teacher.api.TeacherService;
import az.chaypay.teacher.repository.dao.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService service;

    @GetMapping
    ResponseEntity<List<Teacher>> getStudents() {
        return ResponseEntity.ok(service.getTeachers());
    }
}
