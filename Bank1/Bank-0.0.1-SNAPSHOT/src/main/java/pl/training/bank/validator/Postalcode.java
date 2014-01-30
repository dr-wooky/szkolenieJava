package pl.training.bank.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PostalCodeValidator.class)
@Target(ElementType.FIELD)
@Retention((RetentionPolicy.RUNTIME))
public @interface PostalCode {

    String message() default "postalcode";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String format() default "\\d{2}-\\d{3}";
}
