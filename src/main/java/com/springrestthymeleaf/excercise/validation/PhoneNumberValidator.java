package com.springrestthymeleaf.excercise.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator  implements ConstraintValidator<PhoneNumber, String> {

    @Override
    public void initialize(PhoneNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
        Pattern regular = Pattern.compile("\\d{3,4}/(\\d{2}.){2}\\d{2}");
        Pattern landCodePrefix = Pattern.compile("\\+\\d{4,5}/(\\d{2}.){2}\\d{2}");
        Matcher matcher1 = regular.matcher(phoneNumber.trim());
        Matcher matcher2 = landCodePrefix.matcher(phoneNumber.trim());

        return matcher1.matches() || matcher2.matches();
    }
}
