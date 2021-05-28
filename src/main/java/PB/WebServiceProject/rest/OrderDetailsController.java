package PB.WebServiceProject.rest;

import PB.WebServiceProject.services.OrderDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class OrderDetailsController {

  private final OrderDetailsService ordersDetailsService;

    @Operation(description = "Delete order details by id")
    @DeleteMapping("/orderDetails/{id}")
    public ResponseEntity deleteOrder(@Parameter(description = "delete order details by id")
                                       @PathVariable(name = "id") Long id) {
        ordersDetailsService.deleteOrderDetails(id);
        return ResponseEntity.ok().build();
    }


}

