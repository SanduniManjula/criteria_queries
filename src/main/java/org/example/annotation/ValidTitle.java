package org.example.annotation;
import org.example.aspect.ValidTitleValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy= ValidTitleValidator.class)
public @interface ValidTitle {
    String message() default "Title should not start with 'T'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
