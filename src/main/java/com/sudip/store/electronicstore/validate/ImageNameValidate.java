package com.sudip.store.electronicstore.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ImageNameValidator.class)
public @interface ImageNameValidate {

    //    error message
    String message() default "Image name is invalid";

    //    represent the group of constraints
    Class<?>[] groups() default {};

    //    additional information about annotation
    Class<? extends Payload>[] payload() default {};
}
