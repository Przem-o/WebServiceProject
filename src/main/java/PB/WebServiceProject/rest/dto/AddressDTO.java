package PB.WebServiceProject.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class AddressDTO {

    @Schema(description = "client city", example = "Gdansk",required = true)
    @NotBlank(message="city can't be blank")
    @NotNull(message="city can't be null")
    private String city;
    @Schema(description = "client country", example = "Poland",required = true)
    @NotBlank(message="country can't be blank")
    @NotNull(message="country can't be null")
    private String country;

}
