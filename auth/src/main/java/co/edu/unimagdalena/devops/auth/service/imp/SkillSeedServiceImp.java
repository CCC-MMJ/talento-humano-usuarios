package co.edu.unimagdalena.devops.auth.service.imp;

import co.edu.unimagdalena.devops.auth.entity.Skill;
import co.edu.unimagdalena.devops.auth.repository.SkillRepository;
import co.edu.unimagdalena.devops.auth.service.SkillSeedService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SkillSeedServiceImp implements SkillSeedService {
    private final SkillRepository skillRepository;

    @Override
    @Transactional
    public void seed(String name, String type) {
        if (!skillRepository.existsByName(name)) {
            skillRepository.save(new Skill(null, name, type));
        }
    }
}
