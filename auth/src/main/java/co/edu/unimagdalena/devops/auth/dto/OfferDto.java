package co.edu.unimagdalena.devops.auth.dto;

import co.edu.unimagdalena.devops.auth.entity.Postulation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferDto {
    private UUID id;
    private UUID companyId;
    private String title;
    private String description;
    private String type;
    private String requirements;
    private LocalDate publicationDate;
    private LocalDate ClosingDate;
    private List<PostulationDto> postulations;
    private UUID offerId;
    private String status;
}

