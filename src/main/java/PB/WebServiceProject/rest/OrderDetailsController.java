package PB.WebServiceProject.rest;
import PB.WebServiceProject.rest.dto.OrderDetailsDTO;
import PB.WebServiceProject.rest.dto.ProductsDTO;
import PB.WebServiceProject.services.OrderDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderDetailsController {

    private final OrderDetailsService ordersDetailsService;

//    @Operation(description = "Add new orderDetails")
//    @PostMapping("/orderDetails")
//    public OrderDetailsDTO addOrderDetails(@Parameter(description = "add new orderDetails")
//                                                     @Valid @RequestBody OrderDetailsDTO orderDetailsDTO) {
//        return ordersDetailsService.addOrderDetails(orderDetailsDTO);
//    }

    @Operation(description = "Delete order details by id")
    @DeleteMapping("/orderDetails/{id}")
    public ResponseEntity deleteOrder(@Parameter(description = "delete order details by id")
                                      @PathVariable(name = "id") Long id) {
        ordersDetailsService.deleteOrderDetails(id);
        return ResponseEntity.ok().build();
    }
//    @Operation(description = "Get order details by id")
//    @GetMapping("/orderDetails/{id}")
//    public List<OrderDetailsDTO> getOrderDetail(@Parameter(description = "get order details by id")
//                                                @PathVariable(name = "id") Long id) {
//        return ordersDetailsService.getOrderDetails(id);
//    }
    @Operation(description = "Get ordered products by orderId")
    @GetMapping("/orderDetails/{orderId}")
    public List<OrderDetailsDTO> getOrderDetail(@Parameter(description = "Get ordered products by orderId")
                                                @PathVariable(name = "orderId") Long orderId) {
        return ordersDetailsService.getOrderedProducts(orderId);
    }

}

