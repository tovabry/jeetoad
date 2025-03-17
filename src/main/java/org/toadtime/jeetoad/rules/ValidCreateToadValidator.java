package org.toadtime.jeetoad.rules;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.toadtime.jeetoad.dto.CreateToad;


public class ValidCreateToadValidator implements ConstraintValidator<ValidToad, CreateToad> {

    @Override
    public boolean isValid(CreateToad createToad, ConstraintValidatorContext constraintValidatorContext) {
        if (createToad == null) {
            return false;
        }
        boolean isValid = true;

        isValid &= validateName(createToad.name(), constraintValidatorContext);
        isValid &= validateAge(createToad.age(), constraintValidatorContext);
        isValid &= validateGender(createToad.gender(), constraintValidatorContext);
        isValid &= validateWeight(createToad.weight(), constraintValidatorContext);

        return isValid;
    }

    private boolean validateName(String name, ConstraintValidatorContext context) {
        if (!Character.isUpperCase(name.charAt(0))) {
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
        if (age > 40) {
            context.buildConstraintViolationWithTemplate("I don't believe the toad really is that old.")
                    .addPropertyNode("age")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }

    private boolean validateWeight(Integer weight, ConstraintValidatorContext context) {
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
