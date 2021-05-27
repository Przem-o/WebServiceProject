package PB.WebServiceProject.rest.dto;

import PB.WebServiceProject.entities.OrderDetailsEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class OrdersDTO {

    @Schema(description = "id of existing order", example = "1")
    private Long id;
    private String date;
    @Schema(description = "order price", required = true)
    @NotNull
    @Min(0)
    private Double price;
    @Enumerated(EnumType.STRING)
    private Status status;
    private ClientDTO clientDTO;
    private Long clientId;
    private Set<OrderDetailsEntity> orderDetailsEntitySet;


}
