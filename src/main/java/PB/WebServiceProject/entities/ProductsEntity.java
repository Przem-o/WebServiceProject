package PB.WebServiceProject.entities;

import PB.WebServiceProject.Type;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

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
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private Type type;


   @ManyToOne
   @JoinColumn(name = "category_id", nullable = false) // dołączenie kolumny category_id do tworzenia relacji z productCategoryEntity
    private ProductCategoryEntity productCategoryEntity;
}
