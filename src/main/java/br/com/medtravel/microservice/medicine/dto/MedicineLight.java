package br.com.medtravel.microservice.medicine.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MedicineLight {

    @NotNull
    @ApiModelProperty(required = true)
    private String name;
    @NotNull
    @ApiModelProperty(required = true)
    private String description;
}
