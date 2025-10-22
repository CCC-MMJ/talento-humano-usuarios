package co.edu.unimagdalena.devops.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificationDto {
    private UUID id;
    private UUID profileId;
    private String name;
    private String entity;
    private LocalDate Date;
}
