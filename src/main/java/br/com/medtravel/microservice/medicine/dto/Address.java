package br.com.medtravel.microservice.medicine.dto;

import lombok.Data;

@Data
public class Address {
    private String country;
    private String state;
    private String city;
}
