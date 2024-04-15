package de.core.quickplan;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest
//@RunWith(SpringRunner.class)
public class AppointmentControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	//@MockBean
	//IAppointmentService appointmentService;
	
	private static final String API_URL ="/appointment";
	
	@Test
	public void testAddAppointment() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(API_URL)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.content(buildUrlEncodedFormEntity("date", "2024-04-15",
	                "description", "test")))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}

	private String buildUrlEncodedFormEntity(String... params) 
	{
		if((params.length % 2) > 0) 
		{
			throw new IllegalArgumentException("Need to give an even number of parameters");
		}
		    StringBuilder result = new StringBuilder();
		    for (int i=0; i<params.length; i+=2) 
		    {
		        if(i > 0) 
		        {
		        	result.append('&');
		        }
		        try {
		            result.
		            append(URLEncoder.encode(params[i], StandardCharsets.UTF_8.name())).
		            append('=').
		            append(URLEncoder.encode(params[i+1], StandardCharsets.UTF_8.name()));
		        }
		        catch (UnsupportedEncodingException e) {
		            throw new RuntimeException(e);
		        }
		    }
		    return result.toString();
	}
}
