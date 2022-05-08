package az.chaypay.teacher.repository;

import az.chaypay.teacher.repository.dao.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}