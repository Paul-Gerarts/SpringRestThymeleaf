package com.springrestthymeleaf.excercise.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BirthdayValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Birthday {
    String message() default "You can't be born in the future!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
