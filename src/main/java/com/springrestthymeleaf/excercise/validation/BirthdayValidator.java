package com.springrestthymeleaf.excercise.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BirthdayValidator implements ConstraintValidator<Birthday, String> {

    @Override
    public void initialize(Birthday constraintAnnotation) {
    }

    @Override
    public boolean isValid(String birthday, ConstraintValidatorContext constraintValidatorContext) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dayOfBirth = LocalDate.parse(birthday, formatter);
        return dayOfBirth.isBefore(LocalDate.now());
    }
}
