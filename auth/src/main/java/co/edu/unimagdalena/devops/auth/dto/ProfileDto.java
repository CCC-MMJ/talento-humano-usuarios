package co.edu.unimagdalena.devops.auth.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Represents a user's professional profile")
public class ProfileDto {
    @Schema(description = "Unique identifier of the profile", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID id;
    @Schema(description = "Associated user ID", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID idUser;
    @Schema(description = "Highest educational level", example = "Bachelor's Degree")
    private String educationalLevel;
    @Schema(description = "City or locality of residence", example = "Santa Marta")
    private String locality;
    @Schema(description = "Contact phone number", example = "+57 300 123 4567")
    private String phoneNumber;
    @Schema(description = "Job expectations or objectives", example = "Looking for backend developer roles")
    private String expectations;
    @Schema(description = "Identifiers of studies attached to this profile")
    private List<UUID> idStudies;
    @Schema(description = "List of study records")
    private List<StudyDto> studies;
    @Schema(description = "Identifiers of certifications attached to this profile")
    private List<UUID> idCertifications;
    @Schema(description = "List of certification records")
    private List<CertificationDto> certifications;
    @Schema(description = "Identifiers of experiences attached to this profile")
    private List<UUID> idExperiences;
    @Schema(description = "List of work experiences")
    private List<ExperiencieDto> experiencies;
    @Schema(description = "Identifiers of skills attached to this profile")
    private List<UUID> idSkills;
    @Schema(description = "List of skills")
    private List<SkillDto> skills;
}
