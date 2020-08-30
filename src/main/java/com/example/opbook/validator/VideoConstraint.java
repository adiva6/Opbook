package com.example.opbook.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = VideoValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface VideoConstraint {
    String message() default "Invalid video ID";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
