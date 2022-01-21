package com.waracle.cakemgr.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.waracle.cakemgr.dto.CakeDTO;
import com.waracle.cakemgr.service.CakeService;
import com.waracle.cakemgr.utils.CakeUtils;
import com.waracle.cakemgr.validator.validation.CakeFieldValidationMessage;
import com.waracle.cakemgr.validator.validation.CakeValidator;

@RestController
public class CakeController {

	/**
	 * Cake service.
	 */
	@Autowired
	private CakeService cakeService;

	/**
	 * Cake utilities.
	 */
	@Autowired
	private CakeUtils utils;
	
	@RequestMapping(value = "/cakes",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveCakesFromJsonFile() {
		cakeService.saveJsonFile();
		return new ResponseEntity("Saving cakes from cake json url.", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/addCake",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<List<CakeFieldValidationMessage>>
		saveCake(@RequestBody final CakeDTO cakeDto)
		throws BindException {
		CakeValidator validator = new CakeValidator();
		
		WebDataBinder binder = new WebDataBinder(cakeDto);
		binder.setValidator(validator);
		
		// throws BindException if there are binding/validation
		// errors, exception are handled using
		// @ControllerAdvice CakeValidationErrorHandler
		binder.validate();
		binder.close();
		
		CakeDTO response = cakeService.saveCake(utils
				.convertCakeDTOToEntity(cakeDto));
		
		return new ResponseEntity(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<List<CakeDTO>> displayAllCakes() {
		List<CakeDTO> response = cakeService.getAllCakes();
		
		if (response.isEmpty()) {
			return new ResponseEntity("Empty cakes!",
					HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/cake/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<CakeDTO> displayCakeById(
		@PathVariable final Integer id) {

		CakeDTO response = cakeService.getCakeById(id);
		if (null == response) {
			return new ResponseEntity("Cannot find cake with ID",
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(response, HttpStatus.OK);
	}
}
