package br.com.medtravel.microservice.medicine.repository;

import br.com.medtravel.microservice.medicine.po.AddressPO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<AddressPO, Long> {
}
