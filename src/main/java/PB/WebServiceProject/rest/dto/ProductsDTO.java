package PB.WebServiceProject.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class ProductsDTO {
    @Schema(description = "id of existing product", example = "1")
    private Long id;
    @Schema(description = "product brand", example = "Nokia", required = true)
    @NotBlank(message = "product brand can't be blank")
    @NotNull(message = "product brand can't be null")
    private String name;
    @Schema(description = "product price, value cant be negative", example = "999.99", required = true)
    @NotBlank(message = "product price can't be blank")
    @Min(value = 0, message = "min 0")
    private Double price;
    @Schema(description = "category of products", example = "Smartphone", required = true)
    private ProductCategoryDTO productCategoryDTO;

}
