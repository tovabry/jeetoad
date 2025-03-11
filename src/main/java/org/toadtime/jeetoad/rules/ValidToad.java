package org.toadtime.jeetoad.rules;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidToadValidator.class)

public @interface ValidToad {
    String message() default "Not a valid toad";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
