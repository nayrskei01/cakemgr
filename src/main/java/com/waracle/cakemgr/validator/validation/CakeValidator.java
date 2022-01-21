package com.waracle.cakemgr.validator.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.waracle.cakemgr.dto.CakeDTO;

public class CakeValidator implements Validator {

	/**
	 * Initialize class.
	 *
	 * @param clazz
	 * @return boolean
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return CakeDTO.class.isAssignableFrom(clazz);
	}

	/**
	 * The supplied errors instance can be used.
	 * to report any resulting validation errors.
	 *
	 * @param cakeObject
	 * @param errors
	 */
	@Override
	public void validate(Object cakeObject, Errors errors) {
		CakeDTO cakeDto = (CakeDTO) cakeObject;
		CakeFieldValidationMessage message = null;
		
		if (isEmpty(cakeDto.getTitle())) {
			message = new CakeFieldValidationMessage("The title is mandatory field.");
			errors.rejectValue("title", null, message.getMessage());
		}
		
		if (isEmpty(cakeDto.getDesc())) {
			message = new CakeFieldValidationMessage("The description is mandatory field.");
			errors.rejectValue("desc", null, message.getMessage());
		}
		
		if (isEmpty(cakeDto.getImage())) {
			message = new CakeFieldValidationMessage("The image is mandatory field.");
			errors.rejectValue("image", null, message.getMessage());
		}
	}
	
	private boolean isEmpty(String value) {
		return null == value || value.length() == 0;
	}
}
