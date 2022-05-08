package az.chaypay.teacher.service.impl;

import az.chaypay.teacher.api.TeacherService;
import az.chaypay.teacher.repository.TeacherRepository;
import az.chaypay.teacher.repository.dao.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository repository;

    @Override
    public List<Teacher> getTeachers() {
        return repository.findAll();
    }
}
