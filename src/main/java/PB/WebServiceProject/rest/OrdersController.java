//package PB.WebServiceProject.rest;
//
//import PB.WebServiceProject.rest.dto.OrdersDTO;
//import PB.WebServiceProject.rest.dto.ProductsDTO;
//import PB.WebServiceProject.services.OrdersService;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@RequiredArgsConstructor
//@RestController
//public class OrdersController {
//
//    private final OrdersService ordersService;
//
//    @Operation(description = "Get all orders")
//    @GetMapping("/orders")
//    public List<OrdersDTO> getOrders(@Parameter(description = "get orders by id")
//                                     @RequestParam(name = "id", required = false) Long id,
//                                     @Parameter(description = "get orders by minPrice", example = "1")
//                                     @RequestParam(name = "minPrice", required = false) Integer minPrice,
//                                     @Parameter(description = "get orders by maxPrice", example = "10000")
//                                     @RequestParam(name = "maxPrice", required = false) Integer maxPrice) {
//        return ordersService.getOrders(id, minPrice, maxPrice);
//
//    }
//
//    @PostMapping("/order")
//    public OrdersDTO addOrder(@Parameter(description = "add new order")
//                               @Valid @RequestBody OrdersDTO ordersDTO) {
//        return ordersService.addOrder(ordersDTO);
//    }
//
//    @Operation(description = "Get orders by client id")
//    @GetMapping("/orders/{clientId}")
//    public List<OrdersDTO> getClientOrders(@Parameter(description = "clientId")
//                                           @PathVariable(name = "clientId") Long clientId) {
//        return ordersService.findClientOrders(clientId);
//    }
//    @Operation(description = "Delete order by id")
//    @DeleteMapping("/order/{id}")
//    public ResponseEntity deleteOrder(@Parameter(description = "delete order by id")
//                                       @PathVariable(name = "id") Long id) {
//        ordersService.deleteOrder(id);
//        return ResponseEntity.ok().build();
//    }
//
//
//}
//
