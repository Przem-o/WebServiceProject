//package PB.WebServiceProject.rest;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import task4.rest.dto.SmartphoneDTO;
//import task4.services.OrderService;
//
//import java.util.List;
//
//@RestController
//public class OrdersController {
//
//    private final OrderService orderService;
//
//    public OrdersController(OrderService orderService) {
//        this.orderService = orderService;
//    }
//
//    @Operation(description = "Get orders by client id")
//    @GetMapping("/orders/{clientId}")
//    public List<SmartphoneDTO> getOrderedSmartphones(@Parameter(description = "clientId")
//                                                        @PathVariable(name = "clientId") Long clientId) {
//        return orderService.findOrderedSmartphones(clientId);
//    }
//    @Operation(description = "Add smartphone to orders by client id and smartphone id")
//    @PostMapping("/orders/{clientId}/{smartphoneId}")
//    public List<SmartphoneDTO> addSmartphoneToOrderList(@Parameter(description = "Id of client that orders smartphone")
//                                                        @PathVariable(name = "clientId") Long clientId,
//                                                        @Parameter(description = "Id of smartphone that orders client")
//                                                   @PathVariable(name = "smartphoneId") Long smartphoneId){
//        return orderService.addSmartphoneToOrderList(clientId, smartphoneId);
//    }
//    @Operation(description = "Delete smartphone from orders by client id and smartphone id")
//    @DeleteMapping("/orders/{clientId}/{smartphoneId}")
//    public ResponseEntity deleteSmartphoneFromOrderList(@Parameter(description = "Id of client")
//                                                        @PathVariable(name = "clientId") Long clientId,
//                                                        @Parameter(description = "Id of smartphone")
//                                                        @PathVariable(name = "smartphoneId") Long smartphoneId){
//        orderService.deleteSmartphoneFromOrderList(clientId, smartphoneId);
//        return ResponseEntity.ok().build();
//    }
//}
