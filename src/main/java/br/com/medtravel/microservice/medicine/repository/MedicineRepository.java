package br.com.medtravel.microservice.medicine.repository;

import br.com.medtravel.microservice.medicine.po.MedicinePO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends CrudRepository<MedicinePO, Long> {
}
