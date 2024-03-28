package de.core.quickplan.service.misc;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

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
	/**
	 * converts the ModelAndView into an String, that can get returned<br>
	 * TODO test for performance
	 * @param dateFragment
	 * @return the HTML
	 */
	public static String parse(ModelAndView model) {
		Context con = new Context();
		con.setVariables(model.getModel());
		con.setLocale(LocaleContextHolder.getLocale());
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setOrder(1);
        resolver.setCacheable(true);
        templateEngine.addTemplateResolver(resolver);
        return templateEngine.process(model.getViewName(), con);
	}
	/**
	 * an successful-response with the given body
	 * @param <T> the type of body
	 * @param body the body
	 * @return the response-entity for returning
	 */
	public static <T> ResponseEntity<T> success(T body) {
		return ResponseEntity.ok().body(body);
	}
	/**
	 * extracts the body-content of this HTML-String
	 * @param parse
	 * @return null if &lt;body&gt; and &lt;/body don't exist exactly once
	 */
	public static String extractBody(String parse) {
		String[] res = parse.split(RegexService.escape("<body>"));
		if(res.length == 2)
		{
			res = res[1].split(RegexService.escape("</body>"));
			if(res.length == 2)
			{
				return res[0];
			}
		}
		return null;
	}
}
