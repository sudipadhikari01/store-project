package com.sudip.store.electronicstore.validate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ImageNameValidator implements ConstraintValidator<ImageNameValidate, String> {

    private Logger logger = LoggerFactory.getLogger(ImageNameValidator.class);

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        logger.info("Value from field image name is: {} ", s);
        if (s == null) return false;
        else if (s.isBlank()) return false;
        return true;


    }
}
