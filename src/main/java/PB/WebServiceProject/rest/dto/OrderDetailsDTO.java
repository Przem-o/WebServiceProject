//package PB.WebServiceProject.rest.dto;
//
//import io.swagger.v3.oas.annotations.media.Schema;
//import lombok.*;
//
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//import java.util.Set;
//
//@Data
//@Builder
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
//public class OrderDetailsDTO {
//
//    @Schema(description = "id of existing orderDetail", example = "1")
//    private Long id;
//    @Schema(description = "required products id", example = "1", required = true)
//    @NotBlank(message = "products can't be blank")
//    @NotNull(message = "products can't be null")
//    private Set<ProductsDTO> productsDTOS;
//
//    @Schema(description = "required orders id", example = "1", required = true)
//    private OrdersDTO ordersDTO;
//
//    @Schema(description = "quantity of product ordered", example = "1", required = true)
//    @NotBlank(message = "quantity ordered can't be blank")
//    @NotNull(message = "quantity ordered can't be null")
//    private Long quantityOrdered;
//
//}
//
