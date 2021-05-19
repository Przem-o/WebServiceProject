package PB.WebServiceProject.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "productsCategory")
public class ProductCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "category")
//    @Enumerated(EnumType.STRING)
    private String category;


    @OneToMany(mappedBy = "productCategoryEntity")
    private Set<ProductsEntity> productsEntitySet;
}
