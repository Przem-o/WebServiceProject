package PB.WebServiceProject.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsDTO {

//    @Schema(description = "id of existing orderDetail", example = "1")
//    private Long id;
    @Schema(description = "quantity of ordered products", example = "1", required = true)
    @NotNull(message = "quantity of ordered product can't be null")
    private Integer quantity;

    private ProductsDTO productsDTO; // wg patryka tylko to
    private OrdersDTO ordersDTO; //anat ma tylko to

}

