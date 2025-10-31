package co.edu.unimagdalena.devops.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Represents a skill")
public class SkillDto {
    @Schema(description = "Unique identifier of the skill", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    UUID id;
    @Schema(description = "Name of the skill", example = "Java")
    String name;
    @Schema(description = "Type or category of the skill", example = "Technical")
    String type;
}
