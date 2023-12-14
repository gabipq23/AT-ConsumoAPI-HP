package com.example.ATConsumoHP;

import com.example.ATConsumoHP.model.House;
import com.example.ATConsumoHP.util.HouseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class AtConsumoHpApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtConsumoHpApplication.class, args);
		Logger logger = LoggerFactory.getLogger(HouseUtil.class);

		logger.info("Harry Potter API - Information from Hogwarts houses");
		Scanner scanner = new Scanner(System.in);

		logger.info("Choose an option");
		logger.info("1 - Id");
		logger.info("2 - House(Gryffindor, Ravenclaw, Hufflepuff, Slytherin)");
		int choice = scanner.nextInt();
		if(choice!=1 && choice!=2) {
			logger.error("Invalid option");
		}
		HouseUtil houseUtil = new HouseUtil();

		switch (choice) {
			case 1: {
				logger.info("Whats the id? ");
				int idCasa = scanner.nextInt();
				String idNovo = HouseUtil.getIntId(idCasa);
				try {
					House house = houseUtil.getById(idNovo);

					logger.info("Name: " + house.getName());
					logger.info("Animal: " + house.getAnimal());
					logger.info("List of traits: ");
					List<String> traitNames = houseUtil.getTraitByHouseId(idNovo);

					for (String traitName : traitNames){
						logger.info("-" + traitName);
					}

				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				break;
			}
			case 2: {
				logger.info("Whats the name of the house? ");
				String nomeCasa = scanner.next();
				try {
					House house = houseUtil.getByName(nomeCasa);
					logger.info("Name: " + house.getName());
					logger.info("Element: " + house.getElement());
					logger.info("Founder: " + house.getFounder());
					logger.info("House colors: " + house.getHouseColours());
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
				break;
			}
		}
	}

}
