package de.core.quickplan.validation;

import de.core.quickplan.service.misc.TimeService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * checks if an String has the required form to be converted to an time-stamp
 * 
 * @author Sebastian Kohler
 *
 */
public class TimestampValidator implements ConstraintValidator<Timestamp, String> 
{
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
			TimeService.timestamp(value);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
}
