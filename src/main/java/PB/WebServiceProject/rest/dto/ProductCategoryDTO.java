package PB.WebServiceProject.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class ProductCategoryDTO {

    @Id
    private Long id;
    @Schema(description = "product category", example = "Smartphone", required = true)
    @NotBlank(message = "product category can't be blank")
    @NotNull(message = "product category can't be null")
    private String productCategory;

}
