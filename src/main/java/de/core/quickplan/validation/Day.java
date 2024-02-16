package de.core.quickplan.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * annotates an field as an day-string (yyy-mm-dd)<br>
 * allows for blank-values<br>
 * use NotBlank if you want to check this case
 * 
 * @author Sebastian Kohler
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DayValidator.class)
public @interface Day {
	String message() default "error.day";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
