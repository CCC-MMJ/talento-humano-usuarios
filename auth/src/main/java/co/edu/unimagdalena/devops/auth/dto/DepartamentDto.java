package co.edu.unimagdalena.devops.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Representa un departamento/provincia")
public class DepartamentDto {
    @Schema(description = "Identificador único", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID id;

    @Schema(description = "Nombre del departamento", example = "Magdalena")
    private String name;

    @Schema(description = "País al que pertenece", example = "Colombia")
    private String country;
}

