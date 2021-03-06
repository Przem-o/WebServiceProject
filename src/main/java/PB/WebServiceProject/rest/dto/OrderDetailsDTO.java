package PB.WebServiceProject.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsDTO {

    @Schema(description = "id of existing orderDetail", example = "1")
    private Long id;

    private Long ordersId;

    @Schema(description = "quantity of product ordered", example = "1", required = true)
    @NotNull(message = "quantity ordered can't be null")
    private Long quantityOrdered;

}

