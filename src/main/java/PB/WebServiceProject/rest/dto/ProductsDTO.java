package PB.WebServiceProject.rest.dto;

import PB.WebServiceProject.entities.ProductCategoryEntity;
import lombok.*;
import javax.persistence.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class ProductsDTO {

    private Long id;

    private String name;

    private Double price;

    private Long productCategoryId;

//swagger
}
