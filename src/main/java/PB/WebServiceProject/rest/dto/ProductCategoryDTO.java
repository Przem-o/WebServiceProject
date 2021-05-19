package PB.WebServiceProject.rest.dto;

import PB.WebServiceProject.entities.ProductsEntity;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class ProductCategoryDTO {

    private Long id;
    private String category;

    //swagger
}
