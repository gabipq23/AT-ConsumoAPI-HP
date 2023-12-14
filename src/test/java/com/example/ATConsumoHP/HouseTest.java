package com.example.ATConsumoHP;

import com.example.ATConsumoHP.model.House;
import com.example.ATConsumoHP.util.HouseUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class HouseTest {

	Logger logger = LoggerFactory.getLogger(HouseUtil.class);
	@Test
	@DisplayName("Teste para verificar se casa esta correta")
	public void testHouseById() {
		logger.info("");
		HouseUtil houseUtil = new HouseUtil();
		House house = houseUtil.getById("0367baf3-1cb6-4baf-bede-48e17e1cd005");
		assertEquals("Gryffindor", house.getName());
		assertEquals("Lion", house.getAnimal());
	}

	@Test
	@DisplayName("Teste do método getById")
	public void testGetNonExistentHouse() {
		logger.info("Deve lançar exceção ao buscar casa inexistente");
		HouseUtil houseUtil = new HouseUtil();
		assertThrows(RuntimeException.class, () -> {
			houseUtil.getById("umIdQualquer");
		});
	}

	@Test
	@DisplayName("Teste do método getTraitByHouseId")
	public void testGetTraitByHouseId() {
		logger.info("Vai testar se pega a lista de traits");
		HouseUtil houseUtil = new HouseUtil();
		List<String> traits = houseUtil.getTraitByHouseId("0367baf3-1cb6-4baf-bede-48e17e1cd005");
		List<String> expectedTraits = List.of("Courage", "Chivalary", "Nerve", "Daring", "Determination", "Bravery");
		assertEquals(expectedTraits, traits);
	}

	@Test
	@DisplayName("Teste do método getByName")
	public void testGetHouseByName() {
		logger.info("Vai testar se pega o nome da casa corretamente");
		HouseUtil houseUtil = new HouseUtil();
		House house = houseUtil.getByName("Gryffindor");
		assertEquals("Gryffindor", house.getName());

	}

	@Test
	@DisplayName("Teste do método getAllHouses")
	public void testGetAllHouses(){
		logger.info("Vai testar se pega lista com todas as casas");
		HouseUtil houseUtil = new HouseUtil();
		List<House> allHouses = houseUtil.getAllHouses();
		assertEquals(4,allHouses.size());
	}

	@Test
	@DisplayName("Teste do método getIntId")
	public void testGetNewId(){
		logger.info("Vai testar se o id novo faz referencia ao antigo");
		HouseUtil houseUtil = new HouseUtil();
		assertEquals("0367baf3-1cb6-4baf-bede-48e17e1cd005",HouseUtil.getIntId(1));
	}

}
