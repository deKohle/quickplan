package de.core.quickplan.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * annotates an field as an time-stamp-string
 * 
 * @author Sebastian Kohler
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TimestampValidator.class)
public @interface Timestamp {
	String message() default "error.timestamp";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
