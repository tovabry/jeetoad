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
        isValid &= validateGender(updateToad.gender(), constraintValidatorContext);
        isValid &= validateWeight(updateToad.weight(), constraintValidatorContext);

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

    private boolean validateWeight(Integer weight, ConstraintValidatorContext context) {
        if (weight == null) {
            context.disableDefaultConstraintViolation();
            return true;
        }
        if (weight < 0) {
            context.buildConstraintViolationWithTemplate("The weight must not be negative")
                    .addPropertyNode("weight")
                    .addConstraintViolation();
            return false;
        }
        if (weight > 100) {
            context.buildConstraintViolationWithTemplate("You need to take this toad to the veterinarian, it's obese")
                    .addPropertyNode("weight")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

    private boolean validateGender(Character gender, ConstraintValidatorContext context) {
        if (gender == null) {
            context.disableDefaultConstraintViolation();
            return true;
        }
        gender = Character.toLowerCase(gender);

        if (gender != 'm' && gender != 'f') {
            context.buildConstraintViolationWithTemplate("Gender must be 'm' or 'f'")
                    .addPropertyNode("gender")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

}
