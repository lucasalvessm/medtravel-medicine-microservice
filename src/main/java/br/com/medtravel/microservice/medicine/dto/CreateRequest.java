package br.com.medtravel.microservice.medicine.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateRequest extends Medicine {

    private String imageBase64;
    private List<Address> addressInfo;
}
