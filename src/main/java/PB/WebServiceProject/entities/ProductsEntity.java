package PB.WebServiceProject.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "products")
public class ProductsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private Double price;

//    @ManyToOne
//    @JoinColumn(name = "productCategory_id")
//    private ProductCategoryEntity productCategoryEntity;

//    @OneToMany(mappedBy = "productsEntity") //*
//    private Set<OrderDetailsEntity> orderDetailsEntitySet;

}




















//    @OneToMany(mappedBy = "productsEntity")
//    private Set<OrderDetailsEntity> orderDetailsEntitySet;An niemiał

