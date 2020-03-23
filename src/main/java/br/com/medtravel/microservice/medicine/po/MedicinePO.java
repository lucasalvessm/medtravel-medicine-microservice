package br.com.medtravel.microservice.medicine.po;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

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
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn( name = "ID_IMAGE", referencedColumnName = "ID_IMAGE")
    private ImagePO image;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn( name = "ID_MEDICINE", referencedColumnName = "ID_MEDICINE")
    private List<AddressPO> addressList;
}
