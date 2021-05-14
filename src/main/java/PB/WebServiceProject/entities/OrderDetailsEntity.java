package PB.WebServiceProject.entities;

import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Set;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "orderDetails")
public class OrderDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Long ordersId;
    @Column(name = "quantityOrdered")
    private Long quantityOrdered;

    @ManyToOne// powinna być adnotacja OneToMany?
    @JoinColumn(name = "orders_id")
    private Set<OrderDetailsEntity> orderDetailsEntitySet;

    @ManyToOne // powinna być adnotacja OneToMany?
    @JoinColumn(name = "product_id")
    private Set<ProductsEntity> productsEntitySet;

}
