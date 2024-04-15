package de.core.quickplan;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.core.quickplan.controller.AppointmentController;

@SpringBootTest
class QuickplanApplicationTests {

	@Autowired
	private AppointmentController controller;
	
	@Test
	void contextLoads() {
		Assertions.assertThat(controller).isNotNull();
	}

}
