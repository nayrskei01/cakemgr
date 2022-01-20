package com.waracle.cakemgr.service;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.BDDAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.waracle.cakemgr.dto.CakeDTO;
import com.waracle.cakemgr.model.CakeEntity;

@RunWith(MockitoJUnitRunner.class)
public class CakeServiceTest
{
	private CakeService cakeService;
	
	@Before
	public void setup()
	{
		cakeService = mock(CakeService.class);
	}
	
	@Test
	public void testSaveCake_returnCakeDeteails()
	{
		//given
		CakeEntity saveCake = cakeDetails();
		CakeDTO cakeDto = cakeDetailsDTO();
		given(cakeService.saveCake(cakeDetails())).willReturn(cakeDto);
		
		//when
		CakeDTO responseCake = cakeService.saveCake(saveCake);
		
		//then
		then(responseCake).isNotNull();
		then(responseCake.getTitle()).isEqualTo("Lemon cheesecake");
		then(responseCake.getDesc()).isEqualTo("A cheesecake made of lemon");
		then(responseCake.getImage()).isEqualTo("https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg");
	}
	
	@Test
	public void testgetCakeById_returnCakeDetails()
	{
		//given
		CakeEntity saveCake = cakeDetails();
		saveCake.setEmployeeId(1);
		CakeDTO cakeDto = cakeDetailsDTO();
		given(cakeService.getCakeById(any(Integer.class))).willReturn(cakeDto);
		
		//when
		CakeDTO responseCake = cakeService.getCakeById(1);
		
		//then
		then(responseCake.getEmployeeId()).isNotNull();
		then(responseCake.getTitle()).isEqualTo(saveCake.getTitle());
		then(responseCake.getDesc()).isEqualTo(saveCake.getDesc());
		then(responseCake.getImage()).isEqualTo(saveCake.getImage());
	}
	
	@Test
	public void getAllCakes_returnListOfCakesDetails()
	{
		//given
		List<CakeEntity> saveCake = new ArrayList<>();
		saveCake.add(cakeDetails());
		List<CakeDTO> cakeDto = new ArrayList<>();
		cakeDto.add(cakeDetailsDTO());
		given(cakeService.getAllcakes()).willReturn(cakeDto);
		
		//when
		List<CakeDTO> responseCake = cakeService.getAllcakes();
		
		//then
		then(responseCake).isNotNull();
		then(responseCake.get(0).getTitle()).isEqualTo(saveCake.get(0).getTitle());
		then(responseCake.get(0).getDesc()).isEqualTo(saveCake.get(0).getDesc());
		then(responseCake.get(0).getImage()).isEqualTo(saveCake.get(0).getImage());
	}
	
	private CakeEntity cakeDetails()
	{
		return CakeEntity.builder()
				.title("Lemon cheesecake")
				.desc("A cheesecake made of lemon")
				.image("https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg")
				.build();
	}
	
	private CakeDTO cakeDetailsDTO()
	{
		return CakeDTO.builder()
				.employeeId(1)
				.title("Lemon cheesecake")
				.desc("A cheesecake made of lemon")
				.image("https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg")
				.build();
	}
}
