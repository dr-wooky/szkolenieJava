package pl.training.bank.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PostalCodeValidator implements ConstraintValidator<PostalCode, String> {

    private String format;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value.matches(format);
    }

    @Override
    public void initialize(PostalCode postalCode) {
        format = postalCode.format();
    }
}
