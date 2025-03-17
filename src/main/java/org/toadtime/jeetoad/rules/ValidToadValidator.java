package org.toadtime.jeetoad.rules;

import jakarta.persistence.TypedQuery;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.toadtime.jeetoad.dto.CreateToad;


public class ValidToadValidator implements ConstraintValidator<ValidToad, CreateToad> {

    @Override
    public boolean isValid(CreateToad createToad, ConstraintValidatorContext constraintValidatorContext) {
        if (createToad == null) {
            return false;
        }
        boolean isValid = true;

        isValid &= validateName(createToad.name(), constraintValidatorContext);
        isValid &= validateAge(createToad.age(), constraintValidatorContext);

        return isValid;
    }

    private boolean validateName(String name, ConstraintValidatorContext context) {
        if (name == null || name.isEmpty() || !Character.isUpperCase(name.charAt(0))) {
            context.buildConstraintViolationWithTemplate("The name must start with an uppercase letter")
                    .addPropertyNode("name")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

    private boolean validateAge(Integer age, ConstraintValidatorContext context) {
        if (age < 0) {
            context.buildConstraintViolationWithTemplate("The age must not be negative")
            .addPropertyNode("age")
            .addConstraintViolation();
            return false;
        }
        if (age > 40){
            context.buildConstraintViolationWithTemplate("I don't believe the toad really is that old.")
            .addPropertyNode("age")
            .addConstraintViolation();
            return false;
        }
        return true;
    }

}
