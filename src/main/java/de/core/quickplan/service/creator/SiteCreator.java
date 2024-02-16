package de.core.quickplan.service.creator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

import de.core.quickplan.domain.Month;
import jakarta.servlet.http.HttpServletRequest;

/**
 * used to fill the data with the needed data
 * 
 * @author Sebastian Kohler
 *
 */
public class SiteCreator {

	/**
	 * creates the calendar-page
	 * @param calendar
	 * @param templateName
	 * @param request
	 * @return
	 */
	public static ModelAndView getCalendar(Month calendar, HttpServletRequest request)
	{
		Constructor con = new Constructor(request,"calendar");
		con.add("calendar", calendar);
		return con.construct();
		
	}
	
	private static class Constructor
	{
		private Map<String,Object> data;
		private String templateName;
		public Constructor(HttpServletRequest request, String template)
		{
			this.data = new HashMap<String,Object>();
			this.templateName = template;
			data.put("requestString", request.getRequestURI());
		}
		public void add(String key, Object value)
		{
			data.put(key, value);
		}
		public ModelAndView construct()
		{
			return new ModelAndView(templateName,data);
		}
	}
}