package PB.WebServiceProject.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;

import javax.validation.constraints.NotNull;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDTO {

    @Schema(description = "id of existing order", example = "1")
    private Long id;
    private OffsetDateTime date;
    @Schema(description = "total price of the order", required = true)
    @NotNull
    private Double price;
    @Enumerated(EnumType.STRING)
    private Status status;

//    private OrderDetailsDTO orderDetailsDTO;
//    private ClientDTO clientDTO;
    private List<OrderDetailsDTO> orderDetailsDTOSet;


}