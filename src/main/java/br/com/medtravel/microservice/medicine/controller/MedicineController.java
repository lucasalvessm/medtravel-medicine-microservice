package br.com.medtravel.microservice.medicine.controller;

import br.com.medtravel.microservice.medicine.dto.*;
import br.com.medtravel.microservice.medicine.service.MedicineService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("medicines")
@CrossOrigin(origins = "*")
public class MedicineController {

    public static final String COULD_NOT_FIND_ANY_MEDICINE_FOR_PROVIDED_ID = "Could not find any medicine for provided id";

    @Autowired
    private MedicineService medicineService;

    public static final String MEDICINE_ID = "Medicine id";

    @ApiOperation(value = "Create a medicine register")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Return the medicine created")
    })
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MedicineResponse> create(@ApiParam("Medicine object to create")
                                                 @Valid @RequestBody CreateRequest medicine) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicineService.create(medicine));
    }

    @ApiOperation(value = "Delete a medicine register")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Medicine deleted successfully"),
            @ApiResponse(code = 404, message = COULD_NOT_FIND_ANY_MEDICINE_FOR_PROVIDED_ID)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@ApiParam(value = MEDICINE_ID, required = true) @PathVariable(value = "id") long id) {
        try {
            medicineService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "Update a medicine register")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Medicine updated successfully"),
            @ApiResponse(code = 404, message = COULD_NOT_FIND_ANY_MEDICINE_FOR_PROVIDED_ID)
    })
    @PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MedicineResponse> update(@ApiParam("Medicine object to update")
                                                 @Valid @RequestBody UpdateRequest medicine) {
        try {
            return ResponseEntity.ok(medicineService.update(medicine));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiOperation(value = "List all medicines")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Medicines listed successfully")
    })
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ListResponse>> listAll() {
        return ResponseEntity.ok(medicineService.listAll());
    }

    @ApiOperation(value = "Get detail of one medicine")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Medicine retrieved successfully"),
            @ApiResponse(code = 404, message = COULD_NOT_FIND_ANY_MEDICINE_FOR_PROVIDED_ID)
    })
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MedicineResponse> getDetail(@ApiParam(value = MEDICINE_ID, required = true) @PathVariable(value = "id") long id) {
        try {
            return ResponseEntity.ok(medicineService.getDetail(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

    }
}
