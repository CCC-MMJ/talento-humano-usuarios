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
    private String empresa;
    private String cargo;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
