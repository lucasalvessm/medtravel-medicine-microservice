package br.com.medtravel.microservice.medicine.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateRequest extends Medicine {

    @ApiModelProperty(required = true)
    @NotNull
    private Long medicineId;
    private String imageBase64;
    private Address addressInfo;
}
