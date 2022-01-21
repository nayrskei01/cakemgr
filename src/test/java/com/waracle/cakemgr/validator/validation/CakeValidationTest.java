package com.waracle.cakemgr.validator.validation;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.dto.CakeDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class CakeValidationTest {
	/**
	 * MockMvc.
	 */
	@Autowired
	private MockMvc mockMvc;

	/**
	 * Object mapper.
	 */
	private ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * Validator test the error response in every field.
	 *
	 * @throws Exception
	 */
	@Test
	public void testSetMessage_returnErrorMessage()
			throws Exception {
		CakeDTO cakeDto = CakeDTO.builder()
				.title(null).desc(null).image(null).build();
		
		mockMvc.perform(post("/addCake")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(cakeDto)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$[0].message")
						.value("The title is mandatory field."))
				.andExpect(jsonPath("$[1].message")
						.value("The description is mandatory field."))
				.andExpect(jsonPath("$[2].message")
						.value("The image is mandatory field."));
	}
}
