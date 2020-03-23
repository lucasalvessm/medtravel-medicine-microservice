package br.com.medtravel.microservice.medicine.dto;

import lombok.Data;

@Data
public class MedicineResponse extends Medicine {
    private Long medicineId;
    private String image;

}
