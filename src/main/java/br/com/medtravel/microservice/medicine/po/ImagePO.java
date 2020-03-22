package br.com.medtravel.microservice.medicine.po;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "IMAGE")
public class ImagePO {

    public ImagePO(String image) {
        this.imageBase64 = image;
    }

    public ImagePO() {
    }

    @Id
    @Column(name = "ID_IMAGE")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idImage;

    @Column(name = "ID_MEDICINE")
    private Long idMedicine;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "TX_IMAGE_BASE64", length = 16777215)
    private String imageBase64;

}
