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
@Schema(description = "Represents an academic study record for a profile")
public class StudyDto {
    @Schema(description = "Unique identifier of the study", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID id;
    @Schema(description = "Identifier of the related profile", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID profileId;
    @Schema(description = "Title of the study", example = "BSc in Computer Science")
    private String title;
    @Schema(description = "Institution where the study took place", example = "Universidad del Magdalena")
    private String institution;
    @Schema(description = "Start date", example = "2020-01-15")
    private LocalDate startDate;
    @Schema(description = "End date", example = "2024-06-30")
    private LocalDate endDate;
    @Schema(description = "Education level", example = "Undergraduate")
    private String level;
}
