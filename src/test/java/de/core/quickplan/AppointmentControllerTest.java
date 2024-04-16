package de.core.quickplan;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.jayway.jsonpath.JsonPath;

@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest
//@RunWith(SpringRunner.class)
public class AppointmentControllerTest {
	private static final Log logger = LogFactory.getLog(AppointmentControllerTest.class);
	
	@Autowired
	MockMvc mockMvc;
	
	//@MockBean
	//IAppointmentService appointmentService;
	
	private static final String API_URL ="/appointment";
	
	private String uuid = null;
	
	@Test
	public void testAppointmentApi() throws Exception {
		logger.info("testing CREATE");
		testAddAppointment();
		logger.info("testing READ");
		testGetAppointment();
		logger.info("testing UPDATE");
		testUpdateAppointment();
		logger.info("testing DELETE");
		testDeleteAppointment();
	}
	
	public void testAddAppointment() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(API_URL)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.content(buildUrlEncodedFormEntity("date", "2024-04-15",
	                "description", "test")))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}
	
	public void testGetAppointment() throws Exception {
		String result = mockMvc.perform(MockMvcRequestBuilders.get(API_URL)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.content(buildUrlEncodedFormEntity("begin", "2024-04-15 00:01",
					"end", "2024-04-15 23:59")))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andReturn().getResponse().getContentAsString();
		uuid = JsonPath.read(result, "$[0]['identifier']").toString();
		Assert.assertNotNull(uuid);
		mockMvc.perform(MockMvcRequestBuilders.get(API_URL)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.content(buildUrlEncodedFormEntity("identifier", uuid)))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
	}
	
	public void testUpdateAppointment() throws Exception {
		if(uuid == null)
		{
			throw new Exception("can't update because uuid is null");
		}
		String result = mockMvc.perform(MockMvcRequestBuilders.put(API_URL)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.content(buildUrlEncodedFormEntity("identifier", uuid,
					"date", "2024-04-15",
	                "description", "test2")))
		.andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
		.andReturn().getResponse().getContentAsString();
		String description = JsonPath.read(result, "$['description']").toString();
		Assert.assertEquals("test2", description);
	}
	
	public void testDeleteAppointment() throws Exception {
		if(uuid == null)
		{
			throw new Exception("can't delete because uuid is null");
		}
		mockMvc.perform(MockMvcRequestBuilders.delete(API_URL)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.content(buildUrlEncodedFormEntity("identifier", uuid)))
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
