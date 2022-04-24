package com.travix.medusa.busyflights.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckDateValidator implements ConstraintValidator<CheckDateFormat, String> {

    private static final Logger logger = LoggerFactory.getLogger(CheckDateValidator.class);

    private String pattern;

    @Override
    public void initialize(CheckDateFormat constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String object, ConstraintValidatorContext constraintContext) {
        if (object == null) {
            return true;
        }

        try {
            Date date = new SimpleDateFormat(pattern).parse(object);
            return true;
        } catch (Exception e) {
            logger.error("Invalid date: " + e);
            return false;
        }
    }
}

