package co.edu.unimagdalena.devops.auth.service.imp;

import co.edu.unimagdalena.devops.auth.dto.SkillDto;
import co.edu.unimagdalena.devops.auth.entity.Skill;
import co.edu.unimagdalena.devops.auth.mapper.SkillMapper;
import co.edu.unimagdalena.devops.auth.repository.SkillRepository;
import co.edu.unimagdalena.devops.auth.service.SkillSeedService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillSeedServiceImp implements SkillSeedService {
    private final SkillRepository skillRepository;
    private final SkillMapper skillMapper;

    @Override
    @Transactional
    public void seed(String name, String type) {
        if (!skillRepository.existsByName(name)) {
            skillRepository.save(new Skill(null, name, type));
        }
    }

    @Override
    public List<SkillDto> getAllSkills() {
        return skillRepository.findAll().stream()
                .map(skillMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SkillDto> getSkillById(UUID id) {
        return skillRepository.findById(id).map(skillMapper::toDto);
    }

    @Override
    @Transactional
    public SkillDto createSkill(SkillDto skillDto) {
        Skill skill = skillMapper.toEntity(skillDto);
        skill.setId(null);
        Skill saved = skillRepository.save(skill);
        return skillMapper.toDto(saved);
    }

    @Override
    @Transactional
    public SkillDto updateSkill(SkillDto skillDto, UUID id) {
        Skill existing = skillRepository.findById(id).orElseThrow();
        if (skillDto.getName() != null) existing.setName(skillDto.getName());
        existing.setType(skillDto.getType());
        Skill saved = skillRepository.save(existing);
        return skillMapper.toDto(saved);
    }

    @Override
    @Transactional
    public void deleteSkillById(UUID id) {
        skillRepository.deleteById(id);
    }

}
