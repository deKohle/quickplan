package de.core.quickplan.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.core.quickplan.domain.dto.AppointmentDTO;
import jakarta.validation.Valid;

/**
 * all methods regarding an appointment
 * 
 * @author Sebastian Kohler
 *
 */
@RestController
public class AppointmentController {
	private static final Log logger = LogFactory.getLog(AppointmentController.class);
	/**
	 * the URI for appointment-CRUD-operations
	 */
	private static final String ENDPOINT_URI = "/appointment"; 

	/**
	 * used to create a new appointment
	 * @param app
	 * @return
	 */
	@RequestMapping(path=ENDPOINT_URI, method=RequestMethod.POST)
	public ResponseEntity<Void> createAppointment(@ModelAttribute("appointment") @Valid AppointmentDTO app, BindingResult result)
	{
		logger.trace("CREATE an new appointment");
		if(result.hasErrors())
		{
			logger.debug("the given appointment has an error");
			
		}
	}
}
