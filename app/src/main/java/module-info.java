module app {
    requires java.sql;
    requires org.hibernate.orm.core;

    requires spring.boot;
    requires spring.data.jpa;
    requires spring.boot.autoconfigure;

    requires spring.core;
    requires spring.beans;
    requires spring.context;

    requires teacher;
    requires student;

    opens az.chaypay.app to spring.core;
    exports az.chaypay.app to spring.beans, spring.context;
}