module student {
    requires static lombok;
    requires com.fasterxml.jackson.databind;

    requires spring.web;

    exports az.chaypay.student to app;
    exports az.chaypay.student.controller to spring.beans;
    exports az.chaypay.student.service.impl to spring.beans;

    opens az.chaypay.student.controller to spring.core;
    opens az.chaypay.student.repository.dao to spring.core, com.fasterxml.jackson.databind, org.hibernate.orm.core;

    requires teacher;
}