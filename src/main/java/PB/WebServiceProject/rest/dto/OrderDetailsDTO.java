package PB.WebServiceProject.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsDTO {

    @Schema(description = "id of existing orderDetail", example = "1")
    private Long id;
    @Schema(description = "required products id", example = "1", required = true)
    @NotBlank(message = "product id can't be blank")
    @NotNull(message = "product id can't be null")
    private Long productsId; //product DTO
    @Schema(description = "required orders id", example = "1", required = true)
    @NotBlank(message = "orders id can't be blank")
    @NotNull(message = "orders id can't be null")
    private Long ordersId;
    @Schema(description = "quantity of product ordered", example = "1", required = true)
    @NotBlank(message = "quantity ordered can't be blank")
    @NotNull(message = "quantity ordered can't be null")
    private Long quantityOrdered;

}
