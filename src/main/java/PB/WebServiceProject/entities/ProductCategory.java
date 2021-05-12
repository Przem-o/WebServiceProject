package PB.WebServiceProject.entities;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String type;
}
