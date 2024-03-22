package de.core.quickplan.service.misc;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * helps at redirecting a user
 * 
 * @author Sebastian Kohler
 *
 */
public class RedirectService {

	/**
	 * redirects the user to the given URL
	 * @param calendarUri
	 * @return
	 */
	public static ModelAndView redirect(String destination) {
		RedirectView redirect = new RedirectView();
		redirect.setUrl(destination);
		return new ModelAndView(redirect);
	}

}
