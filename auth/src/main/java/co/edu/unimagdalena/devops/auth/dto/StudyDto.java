package co.edu.unimagdalena.devops.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyDto {
    private UUID id;
    private UUID profileId;
    private String title;
    private String institution;
    private LocalDate startDate;
    private LocalDate endDate;
    private String level;
}
