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
    private UUID empresaId;
    private String titulo;
    private String descripcion;
    private String tipo;
    private String requisitos;
    private LocalDate fechaPublicacion;
    private LocalDate fechaCierre;
    private String estado;
}

