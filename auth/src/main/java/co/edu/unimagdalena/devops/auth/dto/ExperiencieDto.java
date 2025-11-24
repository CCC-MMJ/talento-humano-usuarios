package co.edu.unimagdalena.devops.auth.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Represents a work experience associated with a profile")
public class ExperiencieDto {
    @Schema(description = "Unique identifier of the experience", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID id;
    @Schema(description = "Identifier of the related profile", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID profileId;
    @Schema(description = "Company name", example = "Acme Corp")
    private String companyName;
    @Schema(description = "Role or position", example = "Backend Developer")
    private String charge;
    @Schema(description = "Brief description of duties", example = "Developed microservices in Java Spring Boot")
    private String description;
    @Schema(description = "Start date", example = "2023-04-01")
    private LocalDate creationDate;
    @Schema(description = "End date", example = "2024-08-31")
    private LocalDate expirationDate;
}
