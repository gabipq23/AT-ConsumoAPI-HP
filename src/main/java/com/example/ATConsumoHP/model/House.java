package com.example.ATConsumoHP.model;

import lombok.Data;

import java.util.List;

@Data
public class House {
    private String id;
    private String name;
    private String houseColours;
    private String animal;
    private String founder;
    private String element;
    private String ghost;
    private String commonRoom;
    private List<Head> heads;
    private List<Trait> traits;
}


