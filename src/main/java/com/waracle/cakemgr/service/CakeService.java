package com.waracle.cakemgr.service;

import java.util.List;

import com.waracle.cakemgr.dto.CakeDTO;
import com.waracle.cakemgr.model.CakeEntity;

public interface CakeService {

	CakeDTO saveCake(CakeEntity saveCake);

	CakeDTO getCakeById(Integer id);
	
	List<CakeDTO> getAllcakes();
	
	void saveJsonFile();
}
