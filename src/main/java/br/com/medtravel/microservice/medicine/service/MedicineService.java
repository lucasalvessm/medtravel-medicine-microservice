package br.com.medtravel.microservice.medicine.service;

import br.com.medtravel.microservice.medicine.dto.*;

import java.util.List;

public interface MedicineService {
    MedicineResponse update(UpdateRequest medicine);

    void delete(long id);

    MedicineResponse create(CreateRequest medicine);

    List<ListResponse> listAll();

    MedicineResponse getDetail(long id);
}
