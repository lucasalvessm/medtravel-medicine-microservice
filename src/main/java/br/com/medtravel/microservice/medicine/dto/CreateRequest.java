package br.com.medtravel.microservice.medicine.dto;

import lombok.Data;

@Data
public class CreateRequest extends Medicine {

    private String imageBase64;
    private Address addressInfo;
}
