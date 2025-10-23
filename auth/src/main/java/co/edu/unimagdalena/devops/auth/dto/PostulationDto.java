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
    private UUID usuarioId;
    private UUID convocatoriaId;
    private LocalDate fecha;
    private String estado;
}
