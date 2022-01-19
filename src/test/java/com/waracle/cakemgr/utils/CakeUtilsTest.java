package com.waracle.cakemgr.utils;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.waracle.cakemgr.dto.CakeDTO;
import com.waracle.cakemgr.model.CakeEntity;


@RunWith(MockitoJUnitRunner.class)
public class CakeUtilsTest
{
	CakeUtils cakeUtils;
	
	@Before
	public void setup()
	{
		cakeUtils = mock(CakeUtils.class);
	}
	
	@Test
	public void testConvertEntityToDTO_returnDTODetails()
	{
		//given
		CakeEntity cakeEntity = cakeDetails();
		CakeDTO cakeDto = cakeDetailsDTO();
		given(cakeUtils.convertEntityToDTO(cakeEntity)).willReturn(cakeDto);
		
		//when
		CakeDTO responseDto = cakeUtils.convertEntityToDTO(cakeEntity);
		
		//then
		then(responseDto).isNotNull();
		then(responseDto.getTitle()).isEqualTo(cakeEntity.getTitle());
		then(responseDto.getDesc()).isEqualTo(cakeEntity.getDesc());
		then(responseDto.getImage()).isEqualTo(cakeEntity.getImage());
	}
	
	@Test
	public void testConvertListEntityToDTO_returnListDTODetails()
	{
		//given
		List<CakeEntity> cakeList = new ArrayList<>();
		cakeList.add(cakeDetails());
		List<CakeDTO> cakeDtoList = new ArrayList<>();
		cakeDtoList.add(cakeDetailsDTO());
		given(cakeUtils.convertListEntityToDTO(cakeList)).willReturn(cakeDtoList);
		
		//when
		List<CakeDTO> responseCakeList = cakeUtils.convertListEntityToDTO(cakeList);
		
		//then
		then(responseCakeList).isNotNull();
		then(responseCakeList.get(0).getTitle()).isEqualTo(cakeList.get(0).getTitle());
		then(responseCakeList.get(0).getDesc()).isEqualTo(cakeList.get(0).getDesc());
		then(responseCakeList.get(0).getImage()).isEqualTo(cakeList.get(0).getImage());
	}
	
	@Test
	public void testConvertCakeDTOToEntity_returnCakeEntityDetails()
	{
		//given
		CakeDTO cakeDto = cakeDetailsDTO();
		CakeEntity cakeEntity = cakeDetails();
		given(cakeUtils.convertCakeDTOToEntity(cakeDto)).willReturn(cakeEntity);
		
		//then
		CakeEntity cake = cakeUtils.convertCakeDTOToEntity(cakeDto);
		
		//when
		then(cake).isNotNull();
		then(cake.getTitle()).isEqualTo(cakeDto.getTitle());
		then(cake.getDesc()).isEqualTo(cakeDto.getDesc());
		then(cake.getImage()).isEqualTo(cakeDto.getImage());
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
