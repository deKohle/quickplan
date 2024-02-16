package de.core.quickplan.controller;

import java.time.LocalDate;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import de.core.quickplan.domain.Month;
import de.core.quickplan.domain.db.Appointment;
import de.core.quickplan.service.inter.IAppointmentService;

/**
 * the way to view an calendar
 * 
 * @author Sebastian Kohler
 *
 */
@Controller
public class ShowAppointmentsController {
	private static final Log logger = LogFactory.getLog(ShowAppointmentsController.class);
	/**
	 * the URI to show an page with appointments
	 */
	public static final String CALENDAR_URI = "/calendar";
	
	@Autowired
	private IAppointmentService dateService;
	
	@RequestMapping(path=CALENDAR_URI, method=RequestMethod.GET)
	public ModelAndView calendar(@RequestParam(required = false) Integer year, @RequestParam(required = false) Integer month)
	{
		logger.trace("loading the calendar");
		Month calendar = getMonth(year,month);
		List<Appointment> dates = dateService.find(calendar.start(),calendar.end());
		calendar.addAppointments(dates);
		
	}

	/**
	 * get the wanted month
	 * @param year
	 * @param month
	 * @return always a month, if the input was wrong it returns the current month
	 */
	private Month getMonth(Integer year, Integer month) {
		if(year == null)
		{
			if(month == null)
			{
				return new Month(LocalDate.now());
			}
			return new Month(LocalDate.now().getYear(), month);
		}
		if(month == null)
		{
			return new Month(LocalDate.now());
		}
		return new Month(year, month);
	}
}
