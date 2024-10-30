package org.example.annotation;

import org.example.aspect.ValidAuthorValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= ValidAuthorValidator.class)
public @interface ValidAuthor {
    String message() default "Author should start with 'A'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}