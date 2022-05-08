package az.chaypay.app;

import az.chaypay.teacher.TeacherPackage;
import az.chaypay.student.StudentPackage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan(basePackageClasses = {StudentPackage.class, TeacherPackage.class})
@EnableJpaRepositories(basePackageClasses = {StudentPackage.class, TeacherPackage.class})
@SpringBootApplication(scanBasePackageClasses = {StudentPackage.class, TeacherPackage.class})
public class ModulithApplication {
    public static void main(String[] args) {
        SpringApplication.run(ModulithApplication.class, args);
    }
}
