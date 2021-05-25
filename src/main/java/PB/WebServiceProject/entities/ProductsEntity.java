package PB.WebServiceProject.entities;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@ToString
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
    //    @Column(name = "productCategory_id")
//    private Long productCategoryId;
    @ManyToOne
    @JoinColumn(name = "productcategory_id")
    private ProductCategoryEntity productCategoryEntity;











//    @OneToMany(mappedBy = "productsEntity")
//    private Set<OrderDetailsEntity> orderDetailsEntitySet;An niemiał
}
