package co.edu.unimagdalena.devops.auth.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExperiencieDto {
    private UUID id;
    private UUID profileId;
    private String companyName;
    private String charge;
    private String description;
    private LocalDate creationDate;
    private LocalDate expirationDate;
}
