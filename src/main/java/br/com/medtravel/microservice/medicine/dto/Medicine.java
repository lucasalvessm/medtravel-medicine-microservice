package br.com.medtravel.microservice.medicine.dto;

import lombok.Data;

@Data
public class Medicine extends MedicineLight {
    private String composition;
    private String posology;
    private String leaflet;
}
