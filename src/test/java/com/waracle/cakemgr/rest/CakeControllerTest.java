package com.waracle.cakemgr.rest;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;

import com.waracle.cakemgr.dto.CakeDTO;

@RunWith(MockitoJUnitRunner.class)
public class CakeControllerTest
{
	private CakeController cakeController;
	
	@Before
	public void setup()
	{
		cakeController = mock(CakeController.class);
	}
	
	@Test
	public void testSaveCakesFromJsonFile_returnResponseEntity()
	{
		//given
		ResponseEntity setResponse = new ResponseEntity("Saving cakes from cake json url.", HttpStatus.OK);
		given(cakeController.saveCakesFromJsonFile()).willReturn(setResponse);
		
		//when
		ResponseEntity response = cakeController.saveCakesFromJsonFile();
		
		//then
		then(response.getBody()).isEqualTo(setResponse.getBody());
		then(response.getStatusCode()).isEqualTo(setResponse.getStatusCode());
	}
	
	@Test
	public void testSaveCake_returnCakeDTODetails() throws BindException
	{
		//given
		CakeDTO cakeDto = cakeDetailsDTO();
		ResponseEntity setResponse = new ResponseEntity(cakeDto, HttpStatus.OK);
		given(cakeController.saveCake(cakeDto)).willReturn(setResponse);
		
		//when
		ResponseEntity response = cakeController.saveCake(cakeDto);
		
		//then
		then(response.getBody()).isEqualTo(setResponse.getBody());
		then(response.getStatusCode()).isEqualTo(setResponse.getStatusCode());
	}
	
	@Test
	public void testSaveCake_returnBadRequest() throws BindException
	{
		//given
		List<String> message = new ArrayList<>();
		message.add("The title is mandatory field.");
		message.add("The description is mandatory field.");
		message.add("The image is mandatory field.");
		CakeDTO cakeDto = null;
		ResponseEntity setResponse = new ResponseEntity(message, HttpStatus.BAD_REQUEST);
		given(cakeController.saveCake(cakeDto)).willReturn(setResponse);
		
		//when
		ResponseEntity response = cakeController.saveCake(cakeDto);
		
		//then
		then(response.getBody()).isEqualTo(setResponse.getBody());
		then(response.getStatusCode()).isEqualTo(setResponse.getStatusCode());
	}
	
	@Test
	public void testDisplayAllCakes_returnBadRequest()
	{
		//given
		ResponseEntity setResponse = new ResponseEntity("Empty cakes!", HttpStatus.BAD_REQUEST);
		given(cakeController.displayAllCakes()).willReturn(setResponse);
		
		//when
		ResponseEntity response = cakeController.displayAllCakes();
		
		//then
		then(response.getBody()).isEqualTo(setResponse.getBody());
		then(response.getStatusCode()).isEqualTo(setResponse.getStatusCode());
	}
	
	@Test
	public void testDisplayAllCakes_returnListCakeDTODetails()
	{
		//given
		List<CakeDTO> cakeList = new ArrayList<>();
		cakeList.add(cakeDetailsDTO());
		ResponseEntity setResponse = new ResponseEntity(cakeList, HttpStatus.BAD_REQUEST);
		given(cakeController.displayAllCakes()).willReturn(setResponse);
		
		//when
		ResponseEntity response = cakeController.displayAllCakes();
		
		//then
		then(response.getBody()).isEqualTo(setResponse.getBody());
		then(response.getStatusCode()).isEqualTo(setResponse.getStatusCode());
	}
	
	@Test
	public void testDisplayCakeById_returnBadRequest()
	{
		//given
		ResponseEntity setResponse = new ResponseEntity("Cannot find cake with ID", HttpStatus.BAD_REQUEST);
		given(cakeController.displayCakeById(1)).willReturn(setResponse);
		
		//when
		ResponseEntity response = cakeController.displayCakeById(1);
		
		//then
		then(response.getBody()).isEqualTo(setResponse.getBody());
		then(response.getStatusCode()).isEqualTo(setResponse.getStatusCode());
	}
	
	@Test
	public void testDisplayCakeById_returnCakeDTODetails()
	{
		//given
		CakeDTO cakeDto = cakeDetailsDTO();
		ResponseEntity setResponse = new ResponseEntity(cakeDto, HttpStatus.OK);
		given(cakeController.displayCakeById(1)).willReturn(setResponse);
		
		//when
		ResponseEntity response = cakeController.displayCakeById(1);
		
		//then
		then(response.getBody()).isEqualTo(setResponse.getBody());
		then(response.getStatusCode()).isEqualTo(setResponse.getStatusCode());
	}
	
	private CakeDTO cakeDetailsDTO()
	{
		return CakeDTO.builder()
				.title("Lemon cheesecake")
				.desc("A cheesecake made of lemon")
				.image("https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg")
				.build();
	}
}
