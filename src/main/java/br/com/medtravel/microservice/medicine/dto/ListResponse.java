package br.com.medtravel.microservice.medicine.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ListResponse extends MedicineLight {

    @ApiModelProperty(required = true)
    @NotNull
    private Long medicineId;
    private String imageBase64;
}
