package br.com.medtravel.microservice.medicine.po;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ADDRESS")
public class AddressPO {
    @Id
    @Column(name = "ID_ADDRESS")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idAddress;
    @Column(name = "ID_MEDICINE")
    private Long medicineId;
    @Column(name = "TX_COUNTRY")
    private String country;
    @Column(name = "TX_STATE")
    private String state;
    @Column(name = "TX_CITY")
    private String city;
    @Column(name = "IN_VALID_ON")
    private Character validOn;
}
