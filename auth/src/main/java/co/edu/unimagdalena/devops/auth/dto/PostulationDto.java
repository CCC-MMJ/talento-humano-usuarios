package co.edu.unimagdalena.devops.auth.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostulationDto {
    private UUID id;
    private UUID userId;
    private UUID offerId;
    private LocalDate creationDate;
    private String status;
    private String userName;
    private String offerDescription;
    private String offerTitle;
}
