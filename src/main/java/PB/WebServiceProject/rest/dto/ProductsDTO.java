package PB.WebServiceProject.rest.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class ProductsDTO {

    private Long id;
    @Schema(description = "product name", required = true)
    @NotBlank(message = " name can't be blank")
    private String name;
    @Schema(description = "product price", required = true)
    private Double price;
    @Schema(description = "product category", required = true)
    private String category;
    @Schema(description = "details of products ordered", required = true)
    @NotNull
    private Set<OrderDetailsDTO> orderDetailsDTOSet;
    @Schema(description = "category of products", required = true)
    @NotNull
    private ProductCategoryDTO productCategoryDTO;

}
