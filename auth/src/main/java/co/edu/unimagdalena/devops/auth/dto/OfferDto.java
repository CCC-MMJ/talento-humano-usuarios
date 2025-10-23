package co.edu.unimagdalena.devops.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
    private String status;
}

