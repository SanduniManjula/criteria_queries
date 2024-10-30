package org.example.aspect;
import org.example.annotation.ValidTitle;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidTitleValidator implements ConstraintValidator<ValidTitle, String> {

    @Override
    public void initialize(ValidTitle constraintAnnotation) {
       ConstraintValidator.super.initialize(constraintAnnotation);

    }
    @Override
    public boolean isValid(String title, ConstraintValidatorContext context) {
        return title == null || !title.startsWith("The");
    }
}

