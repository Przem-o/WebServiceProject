package PB.WebServiceProject.rest.dto;

import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class OrdersDTO {

    private Long id;

    private OffsetDateTime date;

    private Double price;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Long clientId;

    //swagger
}
