package de.core.quickplan.validation;

import de.core.quickplan.service.misc.TimeService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * checks if an String has the required form to be converted to an date
 * 
 * @author Sebastian Kohler
 *
 */
public class DayValidator implements ConstraintValidator<Day, String> 
{
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		try {
			if(value == null || value.isBlank())
			{
				return true;
			}
			TimeService.date(value);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
}
