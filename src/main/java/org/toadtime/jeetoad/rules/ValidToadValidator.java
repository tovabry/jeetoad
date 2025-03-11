package org.toadtime.jeetoad.rules;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.toadtime.jeetoad.dto.ToadInterface;


public class ValidToadValidator implements ConstraintValidator<ValidToad, ToadInterface> {

    @Override
    public boolean isValid(ToadInterface toadInterface, ConstraintValidatorContext constraintValidatorContext) {
        if (toadInterface == null) {
            return false;
        }
        boolean isValid = true;

        isValid &= validateName(toadInterface.name(), constraintValidatorContext);
        isValid &= validateAge(toadInterface.age(), constraintValidatorContext);

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

    private boolean validateAge(int age, ConstraintValidatorContext context) {
        if (age < 0) {
            context.buildConstraintViolationWithTemplate("The age must not be negative");
        }
        if (age > 40){
            context.buildConstraintViolationWithTemplate("I don't believe the toad really is that old.");
        }
        return true;
    }

}
