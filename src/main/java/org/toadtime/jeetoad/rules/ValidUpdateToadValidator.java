package org.toadtime.jeetoad.rules;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.toadtime.jeetoad.dto.UpdateToad;

public class ValidUpdateToadValidator implements ConstraintValidator<ValidToad, UpdateToad> {

    @Override
    public boolean isValid(UpdateToad updateToad, ConstraintValidatorContext constraintValidatorContext) {
        if (updateToad == null)
            return false;

        boolean isValid = true;
        isValid &= validateName(updateToad.name(), constraintValidatorContext);
        isValid &= validateAge(updateToad.age(), constraintValidatorContext);


        return isValid;


    }

    private boolean validateName(String name, ConstraintValidatorContext context) {
        if (name == null) {
            context.disableDefaultConstraintViolation();
            return true;
        }
        if (!Character.isUpperCase(name.charAt(0))) {
            context.buildConstraintViolationWithTemplate("Name must start with uppercase letter")
                    .addPropertyNode("name")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

    private boolean validateAge(Integer age, ConstraintValidatorContext context) {
        if (age == null) {
            context.disableDefaultConstraintViolation();
            return true;
        }
        if (age < 0) {
            context.buildConstraintViolationWithTemplate("The age must not be negative")
                    .addPropertyNode("age")
                    .addConstraintViolation();
            return false;
        }
        if (age > 40) {
            context.buildConstraintViolationWithTemplate("I don't believe the toad really is that old.")
                    .addPropertyNode("age")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

}
