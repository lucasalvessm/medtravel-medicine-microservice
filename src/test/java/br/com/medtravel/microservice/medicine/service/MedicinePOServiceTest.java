package br.com.medtravel.microservice.medicine.service;


import br.com.medtravel.microservice.medicine.dto.CreateRequest;
import br.com.medtravel.microservice.medicine.dto.UpdateRequest;
import br.com.medtravel.microservice.medicine.po.AddressPO;
import br.com.medtravel.microservice.medicine.po.ImagePO;
import br.com.medtravel.microservice.medicine.po.MedicinePO;
import br.com.medtravel.microservice.medicine.repository.AddressRepository;
import br.com.medtravel.microservice.medicine.repository.MedicineRepository;
import br.com.medtravel.microservice.medicine.service.impl.MedicineServiceImpl;
import br.com.medtravel.microservice.medicine.util.ClassMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MedicinePOServiceTest {


    private MedicinePO medicine = new MedicinePO();
    private AddressPO address = new AddressPO();
    private ImagePO image = new ImagePO();

    {
        medicine.setMedicineId(1L);
        medicine.setName("Doril");
        medicine.setDescription("Remédio pra dor na lombar");
        medicine.setComposition("açucar, tompero e tudo que há de bom");
        medicine.setPosology("de 1 em 1 hora");
        medicine.setLeaflet("contra indicado em casos de suspeita de dengo");
        medicine.setCategoryId(1);
        medicine.setViewCount(22);
        medicine.setImage(image);
        medicine.setAddressList(Arrays.asList(address));

        image.setImageBase64("sdan1asdzzu12");
    }

    @Mock
    private MedicineRepository medicineRepository;

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private MedicineService medicineService = new MedicineServiceImpl();

    @Test
    public void createSuccessfullyTest() {
        when(medicineRepository.save(any())).thenReturn(medicine);

        var request = new CreateRequest();
        var medicineResponse = medicineService.create((CreateRequest) ClassMapper.copyProperties(request, medicine));

        assertEquals(request.getName(), medicineResponse.getName());
        assertEquals(request.getDescription(), medicineResponse.getDescription());
        assertEquals(request.getComposition(), medicineResponse.getComposition());
        assertEquals(request.getPosology(), medicineResponse.getPosology());
        assertEquals(request.getLeaflet(), medicineResponse.getLeaflet());
    }

    @Test
    public void updateSuccessfullyTest() {
        when(medicineRepository.save(any())).thenReturn(medicine);
        when(medicineRepository.existsById(medicine.getMedicineId())).thenReturn(true);

        var request = new UpdateRequest();
        var medicineResponse = medicineService.update((UpdateRequest) ClassMapper.copyProperties(request, medicine));

        assertEquals(request.getMedicineId(), medicineResponse.getMedicineId());
        assertEquals(request.getMedicineId(), medicineResponse.getMedicineId());
        assertEquals(request.getName(), medicineResponse.getName());
        assertEquals(request.getDescription(), medicineResponse.getDescription());
        assertEquals(request.getComposition(), medicineResponse.getComposition());
        assertEquals(request.getPosology(), medicineResponse.getPosology());
        assertEquals(request.getLeaflet(), medicineResponse.getLeaflet());
    }

    @Test
    public void resourceNotFoundOnUpdateTest() {
        var medicineToUpdate = (UpdateRequest) ClassMapper.copyProperties(new UpdateRequest(), this.medicine);
        medicineToUpdate.setMedicineId(2L);
        when(medicineRepository.existsById(2L)).thenReturn(false);
        assertThrows(NoSuchElementException.class, () -> medicineService.update(medicineToUpdate), "Expect error because the resource was not found");
    }

    @Test
    public void listSuccessfullyTest() {
        when(medicineRepository.findAll()).thenReturn(Arrays.asList(medicine, medicine, medicine));

        var medicineList = medicineService.listAll();

        assertEquals(3, medicineList.size());
        assertEquals(medicineList.get(0).getMedicineId(), medicine.getMedicineId());
    }

    @Test
    public void getDetailSuccessfullyTest() {
        medicine.setImage(new ImagePO(""));
        when(medicineRepository.findById(medicine.getMedicineId())).thenReturn(Optional.of(medicine));

        var medicineDetail = medicineService.getDetail(1L);

        assertEquals(medicine.getMedicineId(), medicineDetail.getMedicineId());
    }

    @Test
    public void resourceNotFoundOnGetDetailTest() {
        when(medicineRepository.findById(medicine.getMedicineId())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> medicineService.getDetail(1L), "Expect error because the resource was not found");
    }

    @Test
    public void deleteSuccessfullyTest() {
        when(medicineRepository.existsById(medicine.getMedicineId())).thenReturn(true);
        doNothing().when(medicineRepository).deleteById(medicine.getMedicineId());
        medicineService.delete(1L);
        verify(medicineRepository, atLeastOnce()).deleteById(medicine.getMedicineId());
    }

    @Test
    public void resourceNotFoundOnDeleteTest() {
        when(medicineRepository.existsById(medicine.getMedicineId())).thenReturn(false);
        assertThrows(NoSuchElementException.class, () -> medicineService.delete(1L), "Expect error because the resource was not found");
    }

}
