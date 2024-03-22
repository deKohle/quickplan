package de.core.quickplan.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * does the different configurations giving custom beans
 * 
 * @author Sebastian Kohler
 *
 */
@Configuration
@EnableWebSecurity
//once needed for locale-resolver (now just prevents the static-endpoint)
//@EnableWebMvc
public class BeanConfig implements WebMvcConfigurer {
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		// use when no login needed
		http.authorizeHttpRequests(auth -> auth.anyRequest().anonymous());
		http.csrf(csrf -> csrf.disable());
		return http.build();
	}
	
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
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
