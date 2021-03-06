package br.com.medtravel.microservice.medicine.service.impl;

import br.com.medtravel.microservice.medicine.dto.CreateRequest;
import br.com.medtravel.microservice.medicine.dto.ListResponse;
import br.com.medtravel.microservice.medicine.dto.MedicineResponse;
import br.com.medtravel.microservice.medicine.dto.UpdateRequest;
import br.com.medtravel.microservice.medicine.po.AddressPO;
import br.com.medtravel.microservice.medicine.po.ImagePO;
import br.com.medtravel.microservice.medicine.po.MedicinePO;
import br.com.medtravel.microservice.medicine.repository.MedicineRepository;
import br.com.medtravel.microservice.medicine.service.MedicineService;
import br.com.medtravel.microservice.medicine.util.ClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MedicineServiceImpl implements MedicineService {

    public static final String COULD_NOT_FIND_ANY_RESOURCE_FOR_THIS_ID = "Could not find any resource for this id";

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public MedicineResponse update(UpdateRequest medicine) {
        if (!medicineRepository.existsById(medicine.getMedicineId()))
            throw new NoSuchElementException(COULD_NOT_FIND_ANY_RESOURCE_FOR_THIS_ID);
        var medicineToUpdate = (MedicinePO) ClassMapper.copyProperties(new MedicinePO(), medicine);
        return (MedicineResponse) ClassMapper
                .copyProperties(new MedicineResponse(), medicineRepository.save(medicineToUpdate));
    }

    @Override
    public void delete(long id) {
        if (!medicineRepository.existsById(id))
            throw new NoSuchElementException(COULD_NOT_FIND_ANY_RESOURCE_FOR_THIS_ID);

        medicineRepository.deleteById(id);
    }

    @Override
    @Transactional
    public MedicineResponse create(CreateRequest medicine) {
        var medicineToSave = (MedicinePO) ClassMapper.copyProperties(new MedicinePO(), medicine);

        if (medicine.getAddressInfo() != null && !medicine.getAddressInfo().isEmpty())
            medicineToSave.setAddressList(medicine.getAddressInfo()
                    .stream()
                    .map(address -> (AddressPO) ClassMapper.copyProperties(new AddressPO(), medicine.getAddressInfo())).collect(Collectors.toList()));

        if (medicine.getImageBase64() != null)
            medicineToSave.setImage(new ImagePO(medicine.getImageBase64()));

        var medicineSaved = medicineRepository.save(medicineToSave);
        return (MedicineResponse) ClassMapper.copyProperties(new MedicineResponse(), medicineSaved);
    }

    @Override
    public List<ListResponse> listAll() {
        var medicineList = new ArrayList<ListResponse>();
        medicineRepository
                .findAll()
                .iterator()
                .forEachRemaining(medicinePO -> {
                    ListResponse medicine = (ListResponse) ClassMapper.copyProperties(new ListResponse(), medicinePO);
                    if (Objects.nonNull(medicinePO.getImage()))
                        medicine.setImageBase64(medicinePO.getImage().getImageBase64());

                    medicineList.add(medicine);
                });
        return medicineList;
    }

    @Override
    public MedicineResponse getDetail(long id) {
        var medicine = medicineRepository.findById(id).orElseThrow(NoSuchElementException::new);
        var medicineResponse = (MedicineResponse) ClassMapper.copyProperties(new MedicineResponse(), medicine);

        if (medicine.getImage() != null)
            medicineResponse
                    .setImage(medicine
                            .getImage()
                            .getImageBase64());

        return medicineResponse;
    }
}
