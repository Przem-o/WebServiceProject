//package PB.WebServiceProject.entities;
//import lombok.*;
//import javax.persistence.*;
//import java.util.Set;
//
//@Data
//@Builder
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
//
//@Entity
//@Table(name = "products")
//public class ProductsEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column(name = "name")
//    private String name;
//    @Column(name = "price")
//    private Double price;
////    @Column(name = "productCategory_id")
////    private Long productCategoryId;
//
//    @OneToMany(mappedBy = "productsEntity")
//    private Set<OrderDetailsEntity> orderDetailsEntitySet;
//
//   @ManyToOne
//   @JoinColumn(name = "productCategory_id")
//    private ProductCategoryEntity productCategoryEntity;
//}
