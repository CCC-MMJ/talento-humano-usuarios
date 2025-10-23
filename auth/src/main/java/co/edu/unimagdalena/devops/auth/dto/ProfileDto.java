package co.edu.unimagdalena.devops.auth.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    private UUID id;
    private UUID idUser;
    private String educationalLevel;
    private String locality;
    private String phoneNumber;
    private String expectations;
    private List<UUID> idStudies;
    private List<StudyDto> studies;
    private List<UUID> idCertifications;
    private List<CertificationDto> certifications;
    private List<UUID> idExperiences;
    private List<ExperiencieDto> experiencies;
    private List<UUID> idSkills;
    private List<SkillDto> skills;
}
