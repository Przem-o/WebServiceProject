//package PB.WebServiceProject.rest.dto;
//
//import io.swagger.v3.oas.annotations.media.Schema;
//import lombok.*;
//
//import javax.persistence.*;
//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//import java.time.OffsetDateTime;
//import java.util.Set;
//
//@Data
//@Builder
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
//
//public class OrdersDTO {
//
//    @Schema(description = "id of existing order", example = "1")
//    private Long id;
//    private OffsetDateTime date;
//    @Schema(description = "order price", required = true)
//    @NotNull
//    @Min(0)
//    private Double price;
//    @Schema(description = "status enum", required = true)
//    @Enumerated(EnumType.STRING)
//    private Status status;
//    @Schema(description = "client order", required = true)
//    private ClientDTO clientDTO;

//    @Schema(description = " client order details", required = true)
//    private Set<OrderDetailsDTO> orderDetailsDTOSet;
//
//}
