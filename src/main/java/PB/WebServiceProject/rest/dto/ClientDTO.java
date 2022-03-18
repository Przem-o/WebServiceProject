package PB.WebServiceProject.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {


    @Schema(description = "id of existing client", example = "1")
    private Long id;
    @Schema(description = "client name", example = "Jaro", required = true)
    @NotBlank(message = "name can't be blank") // dotyczy tylko stringów
    @NotNull(message = "name can't be null") //adnotacja mówi że nie można podać spacji i białych znaków
    private String name;
    @Schema(description = "client address", required = true)
//    @NotNull(message = "client address can't be null")
//    @NotBlank(message = "client address can't be blank") nie może być tych adnotacji przy polach dot.obiektów
    private AddressDTO addressDTO;
//    private Set<OrdersDTO> ordersDTOSet;

}

