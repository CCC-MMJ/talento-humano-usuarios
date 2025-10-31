package co.edu.unimagdalena.devops.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Represents a certification obtained by a user")
public class CertificationDto {
    @Schema(description = "Unique identifier of the certification", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID id;
    @Schema(description = "Identifier of the related profile", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID profileId;
    @Schema(description = "Name of the certification", example = "AWS Solutions Architect Associate")
    private String name;
    @Schema(description = "Issuing entity", example = "Amazon Web Services")
    private String entity;
    @Schema(description = "Date of issue", example = "2024-01-15")
    private LocalDate Date;
}
