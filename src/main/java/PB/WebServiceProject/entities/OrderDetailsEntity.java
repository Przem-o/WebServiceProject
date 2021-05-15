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

    @ManyToOne
    @JoinColumn(name = "orders_id")
    private OrdersEntity ordersEntity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductsEntity productsEntitySet;

}
