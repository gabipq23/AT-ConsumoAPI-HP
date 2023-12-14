package com.example.ATConsumoHP.util;

import com.example.ATConsumoHP.model.House;
import com.example.ATConsumoHP.model.Trait;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")

public class HouseUtil {

    Logger logger = LoggerFactory.getLogger(HouseUtil.class);

    @GetMapping("Houses/{id}")
    public House getById(@PathVariable String id) {
        String uri = "https://wizard-world-api.herokuapp.com/Houses/" + id;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI(uri))
                    .version(HttpClient.Version.HTTP_2)
                    .build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() == 200){
                ObjectMapper objectMapper = new ObjectMapper();
                logger.info("Success! (code " + response.statusCode() +" )");
                return objectMapper.readValue(response.body(), House.class);
            }else{
                logger.error("API request failed (code " + response.statusCode() + " )");
                throw new RuntimeException("API failed");
            }
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getTraitByHouseId(String id) {
        House house = getById(id);

        if (house != null) {
            List<Trait> traits = house.getTraits();
            List<String> traitNames = new ArrayList<>();
            for (Trait trait : traits) {
                traitNames.add(trait.getName());
            }
            return traitNames;
        } else {
            return List.of();
        }
    }

    public House getByName(String name) {
        List<House> allHouses = getAllHouses();
        for (House house : allHouses) {
            if (house.getName().equalsIgnoreCase(name)) {
                return house;
            }
        }
        return null;
    }

    public List<House> getAllHouses() {
        String uri = "https://wizard-world-api.herokuapp.com/Houses";

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI(uri))
                    .version(HttpClient.Version.HTTP_2)
                    .build();
            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() == 200){
                ObjectMapper objectMapper = new ObjectMapper();
                logger.info("Success! (code " + response.statusCode() +" )");
                return objectMapper.readValue(response.body(), new TypeReference<>() {
                });
            }else {
                logger.error("API request failed (code " + response.statusCode() + " )");
                throw new RuntimeException("API failed");
            }
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getIntId(int number){
        Map<Integer, String> convertId = new HashMap<>();
        convertId.put(1, "0367baf3-1cb6-4baf-bede-48e17e1cd005");
        convertId.put(2, "805fd37a-65ae-4fe5-b336-d767b8b7c73a");
        convertId.put(3, "85af6295-fd01-4170-a10b-963dd51dce14");
        convertId.put(4, "a9704c47-f92e-40a4-8771-ed1899c9b9c1");
        return convertId.getOrDefault(number,null);
    }
}