package az.chaypay.teacher.api;

import az.chaypay.teacher.repository.dao.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> getTeachers();
}
