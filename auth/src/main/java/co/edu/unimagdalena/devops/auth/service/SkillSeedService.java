package co.edu.unimagdalena.devops.auth.service;

import co.edu.unimagdalena.devops.auth.dto.SkillDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SkillSeedService {
    void seed(String name, String type);

    List<SkillDto> getAllSkills();
    Optional<SkillDto> getSkillById(UUID id);
    SkillDto createSkill(SkillDto skillDto);
    SkillDto updateSkill(SkillDto skillDto, UUID id);
    void deleteSkillById(UUID id);
}
