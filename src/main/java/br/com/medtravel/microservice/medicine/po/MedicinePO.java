package br.com.medtravel.microservice.medicine.po;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "MEDICINE")
public class MedicinePO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_MEDICINE")
    private Long medicineId;
    @Column(name = "TX_NAME")
    private String name;
    @Column(name = "TX_DESCRIPTION")
    private String description;
    @Column(name = "TX_COMPOSITION")
    private String composition;
    @Column(name = "TX_POSOLOGY")
    private String posology;
    @Column(name = "TX_LEAFLET")
    private String leaflet;
    @Column(name = "ID_CATEGORY")
    private Integer categoryId;
    @Column(name = "CT_VIEW")
    private Integer viewCount;
    @Column(name = "IN_VALID_ON")
    private Character validOn;
    private ImagePO image;
    private AddressPO address;
}
