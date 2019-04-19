package pl.jurczak.kamil.customers.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = IsFileSelectedValidator.class)
@Documented
public @interface IsFileSelected {
    String message() default "Please select a file";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
