package com.waracle.cakemgr.service.impl;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.dto.CakeDTO;
import com.waracle.cakemgr.model.CakeEntity;
import com.waracle.cakemgr.repository.CakeRepository;
import com.waracle.cakemgr.service.CakeService;
import com.waracle.cakemgr.utils.CakeUtils;

@Service
public class CakeServiceImpl implements CakeService
{
	private static final Logger logger = LoggerFactory.getLogger(CakeServiceImpl.class);
	
	/**
	 * Cake json url.
	 */
	@Value("${waracle.cake.url}")
	private String cake_url;
	
	/**
	 * Repository for CakeEntity.
	 */
	@Autowired
	private CakeRepository cakeRepository;
	
	/**
	 * Cake utilities.
	 */
	@Autowired
	private CakeUtils cakeUtils;
	
	private ObjectMapper objectMapper = new ObjectMapper().configure(
            DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	
	/**
	 * Save cake to cakeEntity.
	 * 
	 * @param cake
	 * @return CakeEntity
	 */
	@Override
	public CakeDTO saveCake(CakeEntity cake)
	{
		CakeDTO responseCake = null;
		if (null != cake)
		{
			responseCake = cakeUtils.convertEntityToDTO(cakeRepository.save(cake));
			logger.info("Saving cakes to Employee DB with ID = " + responseCake.getEmployeeId());
		}
		return responseCake;
	}

	/**
	 * Get cake details by id;
	 * 
	 * @param cake
	 * @return CakeEntity
	 */
	@Override
	public CakeDTO getCakeById(Integer id)
	{
		CakeDTO cakeDto = null;
		logger.info("Fetch cake by ID = " + id);
		Optional<CakeEntity> cake = cakeRepository.findById(id);
		
		if (cake.isPresent())
		{
			cakeDto = cakeUtils.convertEntityToDTO(cake.get());
		}
		return cakeDto;
	}

	/**
	 * Gets all cake list from DB.
	 * 
	 * @return list of CakeEntity
	 */
	@Override
	public List<CakeDTO> getAllCakes()
	{	
		List<CakeDTO> cakesDto = new ArrayList<>();
		
		logger.info("Fetching all cakes from Employee DB");
		List<CakeEntity> cakes = cakeRepository.findAll();
		
		if (!cakes.isEmpty())
		{
			cakesDto = cakeUtils.convertListEntityToDTO(cakes);
		}
		return cakesDto;
	}
	
	/**
	 * Method to save all cakes from cakes JSON link.
	 */
	@Override
	public void saveJsonFile()
	{
		try
		{
			logger.info("[START] downloading cake json");
			HttpResponse<String> response = downloadCakeJson().get();
			logger.info("[END] downloading cake json");
			if (HttpStatus.OK.value() == response.statusCode())
			{
				List<CakeEntity> cakes = objectMapper.readValue(response.body(),
											new TypeReference<List<CakeEntity>>(){});
				logger.info("[START] saving all cakes to Employee DB");
				for (CakeEntity cake: cakes)
				{
					cakeRepository.save(cake);
				}
				logger.info("[END] saving all cakes to Employee DB");
			}
		} catch(Exception e)
		{
			logger.error("ERROR during saving with error message: " + e.getMessage());
		}
	}
	
	private CompletableFuture<HttpResponse<String>> downloadCakeJson() throws Exception
	{
		var httpClient = HttpClient.newBuilder().build();
		
		var httpRequest = HttpRequest.newBuilder(URI.
				create(cake_url)).
				header(HttpHeaders.CONTENT_TYPE,
					MediaType.APPLICATION_JSON_VALUE).
				GET().build();
		
		return httpClient.sendAsync(httpRequest,
				BodyHandlers.ofString());
	}
}
