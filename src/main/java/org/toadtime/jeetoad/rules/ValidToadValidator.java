package org.toadtime.jeetoad.rules;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.toadtime.jeetoad.dto.CreateToad;
import org.toadtime.jeetoad.entity.Toad;

public class ValidToadValidator implements ConstraintValidator<ValidToad, CreateToad> {
    @Override
    public boolean isValid(CreateToad toad, ConstraintValidatorContext constraintContext) {
        //check if the toad is valid or not

        return false;
    }
}
