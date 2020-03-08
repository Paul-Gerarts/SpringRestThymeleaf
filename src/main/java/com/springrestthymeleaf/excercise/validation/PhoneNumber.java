package com.springrestthymeleaf.excercise.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {
    String message() default "The phonenumber must match xxx/xx.xx.xx, xxxx/xx.xx.xx, +xxxx/xx.xx.xx or +xxxxx/xx.xx.xx";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
