package de.core.quickplan.service.misc;

import org.springframework.http.ResponseEntity;

/**
 * helps at creating an response
 * 
 * @author Sebastian Kohler
 *
 */
public class ResponseService {
	/**
	 * @return an error-response
	 */
	public static ResponseEntity<Void> error()
	{
		return ResponseEntity.badRequest().build();
	}
	/**
	 * @return an success-response
	 */
	public static ResponseEntity<Void> success()
	{
		return ResponseEntity.ok().build();
	}
}
