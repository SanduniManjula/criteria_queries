package org.example.aspect;

import org.example.annotation.ValidAuthor;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidAuthorValidator implements ConstraintValidator<ValidAuthor, String> {
    @Override
    public void initialize(ValidAuthor constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);

    }
    @Override
    public boolean isValid(String author, ConstraintValidatorContext context) {
        return author == null || author.startsWith("A");
    }
}
