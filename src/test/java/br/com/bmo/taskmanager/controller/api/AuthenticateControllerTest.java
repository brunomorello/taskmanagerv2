package br.com.bmo.taskmanager.controller.api;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AuthenticateControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void shouldNotAuthenticateIncorrectUser() throws Exception {
		URI uri = new URI("/auth"); 
		String payload = "{\"username\": \"test\", \"password\": \"333333\"}";
		
		mockMvc.perform(MockMvcRequestBuilders.post(uri)
				.content(payload)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(400));
	}
}
