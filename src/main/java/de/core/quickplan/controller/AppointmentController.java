package de.core.quickplan.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.core.quickplan.domain.Month;
import de.core.quickplan.domain.dto.AppointmentDto;
import de.core.quickplan.service.creator.SiteCreator;
import de.core.quickplan.service.inter.IAppointmentService;
import de.core.quickplan.service.misc.IterableService;
import de.core.quickplan.service.misc.ResponseService;
import de.core.quickplan.service.misc.TimeService;
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
	
	@Autowired
	private IAppointmentService dateService;

	/**
	 * used to create an new appointment
	 * @param app
	 * @return
	 */
	@RequestMapping(path=ENDPOINT_URI, method=RequestMethod.POST)
	public ResponseEntity<?> createAppointment(@Valid @ModelAttribute("appointment") AppointmentDto app, 
			BindingResult result)
	{
		logger.trace("CREATE an new appointment");
		if(result.hasErrors())
		{
			logger.debug("the given appointment has an error");
			return ResponseService.error();
		}
		try {
			AppointmentDto finalApp = dateService.create(app);
			return ResponseService.success(ResponseService.extractBody(ResponseService.parse(SiteCreator.dateFragment(finalApp))));
		}
		catch(Exception e)
		{
			logger.info("could not create an appointment");
			return ResponseService.error();
		}
	}
	
	/**
	 * used to update an new appointment
	 * @param app
	 * @return
	 */
	@RequestMapping(path=ENDPOINT_URI, method=RequestMethod.PUT)
	public ResponseEntity<?> updateAppointment(@Valid @ModelAttribute("appointment") AppointmentDto app, 
			BindingResult result)
	{
		logger.trace("UPDATE an appointment");
		if(result.hasErrors())
		{
			logger.debug("the given appointment has an error");
			return ResponseService.error();
		}
		try {
			AppointmentDto finalApp = dateService.update(app);
			return ResponseService.success(finalApp);
		}
		catch(Exception e)
		{
			logger.info("could not update an appointment");
			return ResponseService.error();
		}
	}
	
	/**
	 * used to update an new appointment
	 * @param app
	 * @return
	 */
	@RequestMapping(path=ENDPOINT_URI, method=RequestMethod.GET)
	public List<AppointmentDto> readAppointment(@Valid @ModelAttribute("appointment") AppointmentDto app)
	{
		try {
			if(app.getIdentifier() != null)
			{
				logger.trace("READING an single appointment");
				AppointmentDto result = dateService.read(app.getIdentifier());
				List<AppointmentDto> list = new ArrayList<AppointmentDto>();
				list.add(result);
				return list;
			}
			logger.trace("READING multiple appointments");
			return IterableService.convert(
					dateService.find(TimeService.timestamp(app.getBegin()),TimeService.timestamp(app.getEnd()))
					,AppointmentDto::new
			);
		}
		catch(Exception e)
		{
			logger.info("could not read appointments");
			return null;
		}
	}
	
	/**
	 * used to delete an appointment
	 * @param app
	 * @return
	 */
	//@RequestMapping(path=ENDPOINT_URI+"/delete/{uuid}", method=RequestMethod.DELETE)
	//public ResponseEntity<Void> deleteAppointment(@PathVariable("uuid") String uuid)
	@RequestMapping(path=ENDPOINT_URI, method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteAppointment(@ModelAttribute("appointment") AppointmentDto app)
	{
		logger.trace("DELETE an appointment");
		try {
			dateService.delete(app.getIdentifier());
		}
		catch(Exception e)
		{
			logger.info("could not delete an appointment");
			return ResponseService.error();
		}
		return ResponseService.success();
	}
	
	//@RequestMapping(path="/test", method=RequestMethod.GET)
	public ResponseEntity<Void> test()
	{
		logger.trace("test");
		System.out.print(new Month(LocalDate.now()).toString());
		System.out.print("\n\n\n");
		for(int year = 2000; year < 2050; ++year)
		{
			for(int i = 1; i<=12;++i)
			{
				System.out.print(new Month(year,i).toString());
				System.out.print("\n\n");
			}
		}
		return ResponseService.success();
	}
}
