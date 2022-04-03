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
@Table(name = "productcategory")
public class ProductCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "category")
    private String category;

    @OneToMany(mappedBy = "productCategoryEntity")
    private Set<ProductsEntity> productsEntitySet;
}
