package pl.training.bank.service.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Qualifier
@Component
@Target({ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME) //present in Runtime
public @interface DAO {

    Type type() default Type.IN_MEMORY;

    enum Type {
        IN_MEMORY, JDBC, HIBERNATE, JPA, SPRING_DATA
    }
}
