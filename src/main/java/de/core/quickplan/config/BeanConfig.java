package de.core.quickplan.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * does the different configurations giving custom beans
 * 
 * @author Sebastian Kohler
 *
 */
@Configuration
public class BeanConfig {
	
	/**
	 * to allow for i18n it configures, that the locale gets saved for the session<br>
	 * it also sets the default locale for new sessions
	 * @return
	 */
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.GERMANY);
		return slr;
	}
	/**
	 * used to change the currently selected language
	 * @return
	 */
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("language");
		return lci;
	}
}
