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
    @Column(name = "type")
   // @Enumerated(EnumType.STRING)
    private Long productCategoryId;


   @ManyToOne
   @JoinColumn(name = "productCategory_id")
    private ProductCategoryEntity productCategoryEntity;
}
