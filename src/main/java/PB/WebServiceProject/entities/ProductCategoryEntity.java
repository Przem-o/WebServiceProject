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
@Table(name = "productsCategory")
public class ProductCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "type")
    private Type type;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Set<ProductsEntity> productsEntitySet;
}
