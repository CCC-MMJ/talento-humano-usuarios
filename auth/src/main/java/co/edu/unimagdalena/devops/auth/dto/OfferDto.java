package co.edu.unimagdalena.devops.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Represents a job offer")
public class OfferDto {
    @Schema(description = "Unique identifier of the offer", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID id;
    @Schema(description = "Identifier of the company that posted the offer", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID companyId;
    @Schema(description = "Title of the job offer", example = "Senior Backend Developer")
    private String title;
    @Schema(description = "Detailed description of the job")
    private String description;
    @Schema(description = "Type of job", example = "Full-time")
    private String type;
    @Schema(description = "Requirements for the position", example = "5+ years of experience in Java, Spring Boot")
    private String requirements;
    @Schema(description = "Date when the offer was published", example = "2025-10-01")
    private LocalDate publicationDate;
    @Schema(description = "Closing date for applications", example = "2025-11-15")
    private LocalDate ClosingDate;
    @Schema(description = "List of postulations received for this offer")
    private List<PostulationDto> postulations;
    @Schema(description = "Related offer identifier (if applicable)")
    private UUID offerId;
    @Schema(description = "Current status of the offer", example = "OPEN")
    private String status;
}

