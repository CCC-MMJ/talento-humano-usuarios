package co.edu.unimagdalena.devops.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Represents a company entity")
public class CompanyDto {
    @Schema(description = "Unique identifier of the company", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID id;
    @Schema(description = "Company name", example = "Talento Local S.A.")
    private String name;
    @Schema(description = "Company description", example = "We connect talent with opportunities")
    private String description;
    @Schema(description = "Industry sector", example = "Technology")
    private String sector;
    @Schema(description = "Tax identification number", example = "900123456-7")
    private String nit;
    @Schema(description = "Company email", example = "contact@talentolocal.com")
    private String email;
    @Schema(description = "Company phone number", example = "+57 601 123 4567")
    private String numberPhone;
}

