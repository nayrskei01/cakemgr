package com.waracle.cakemgr.repository;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.waracle.cakemgr.model.CakeEntity;

@DataJpaTest
public class CakeRepositoryTest {
	@Autowired
	private CakeRepository cakeRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Test
	void testGetEmployee_returnEmployeeDetails() {
		//given
		CakeEntity saveCake = CakeEntity.builder()
				.title("Lemon cheesecake")
				.desc("A cheesecake made of lemon")
				.image("https://s3-eu-west-1.amazonaws.com/s3.mediafileserver.co.uk/carnation/WebFiles/RecipeImages/lemoncheesecake_lg.jpg")
				.build();
		testEntityManager.persistFlushFind(saveCake);
		
		//when
		CakeEntity cake = cakeRepository.findByCakeTitle("Lemon cheesecake");
		
		//then
		then(cake.getEmployeeId()).isNotNull();
		then(cake.getTitle()).isEqualTo(saveCake.getTitle());
		then(cake.getDesc()).isEqualTo(saveCake.getDesc());
		then(cake.getImage()).isEqualTo(saveCake.getImage());
	}
}
