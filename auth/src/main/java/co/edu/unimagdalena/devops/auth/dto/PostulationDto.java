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
@Schema(description = "Represents a user's postulation to an offer")
public class PostulationDto {
    @Schema(description = "Unique identifier of the postulation", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID id;
    @Schema(description = "Identifier of the user who applied", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID userId;
    @Schema(description = "Identifier of the offer applied to", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID offerId;
    @Schema(description = "Date when the postulation was created", example = "2024-10-01")
    private LocalDate creationDate;
    @Schema(description = "Current status of the postulation", example = "PENDING")
    private String status;
    @Schema(description = "Name of the applicant", example = "Jane Doe")
    private String userName;
    @Schema(description = "Description of the offer", example = "Backend developer position")
    private String offerDescription;
    @Schema(description = "Title of the offer", example = "Backend Developer")
    private String offerTitle;
}
