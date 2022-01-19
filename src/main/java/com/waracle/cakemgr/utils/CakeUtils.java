package com.waracle.cakemgr.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.waracle.cakemgr.dto.CakeDTO;
import com.waracle.cakemgr.model.CakeEntity;

@Component
public class CakeUtils
{
	@Autowired
	private ModelMapper mapper;
	
	/**
	 * Method to convert CakeEntity to CakeDto.
	 * 
	 * @param cake
	 * @return cakeDto details
	 */
	public CakeDTO convertEntityToDTO(CakeEntity cake)
	{
		return mapper.map(cake, CakeDTO.class);
	}
	
	/**
	 * Method to convert list of CakeEntity to list of CakeDTO.
	 * 
	 * @param cakes
	 * @return list of CakeDTO
	 */
	public List<CakeDTO> convertListEntityToDTO(List<CakeEntity> cakes)
	{
		return cakes.stream().map(cake -> convertEntityToDTO(cake)).collect(Collectors.toList());
	}

	/**
	 * Method to convert CakeDTO to CakeEntity.
	 * 
	 * @param cakeDto
	 * @return CakeEntity details
	 */
	public CakeEntity convertCakeDTOToEntity(CakeDTO cakeDto)
	{
		return mapper.map(cakeDto, CakeEntity.class);
	}
}
