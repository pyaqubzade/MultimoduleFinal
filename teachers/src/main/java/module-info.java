module teacher {
    requires static lombok;

    requires spring.web;

    exports az.chaypay.teacher to app;
    exports az.chaypay.teacher.api to student;
    exports az.chaypay.teacher.controller to spring.beans;
    exports az.chaypay.teacher.service.impl to spring.beans;

    opens az.chaypay.teacher.controller to spring.core;
    opens az.chaypay.teacher.repository.dao;
}