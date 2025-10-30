package co.edu.unimagdalena.devops.auth.service.imp;

import co.edu.unimagdalena.devops.auth.dto.StudyDto;
import co.edu.unimagdalena.devops.auth.entity.Profile;
import co.edu.unimagdalena.devops.auth.entity.Study;
import co.edu.unimagdalena.devops.auth.mapper.StudyMapper;
import co.edu.unimagdalena.devops.auth.repository.ProfileRepository;
import co.edu.unimagdalena.devops.auth.repository.StudyRepository;
import co.edu.unimagdalena.devops.auth.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudyServiceImp implements StudyService {
    private final StudyRepository studyRepository;
    private final StudyMapper studyMapper;
    private ProfileRepository profileRepository;





    @Override
    public List<StudyDto> getAllStudies() {
        return studyRepository.findAll().stream().map(studyMapper::toDto).toList();
    }

    @Override
    public Optional<StudyDto> getStudyById(UUID id) {
        return studyRepository.findById(id).map(studyMapper::toDto);
    }

    @Override
    public StudyDto createStudy(StudyDto studyDto) {
        return studyMapper.toDto(studyRepository.save(studyMapper.toEntity(studyDto)));
    }

    @Override
    public void deleteStudyById(UUID id) {
        studyRepository.deleteById(id);
    }

    @Override
    public StudyDto addStudy(UUID profileId, StudyDto dto) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado"));

        Study study = studyMapper.toEntity(dto);
        study.setProfile(profile);

        return studyMapper.toDto(studyRepository.save(study));
    }

    @Override
    public StudyDto updateStudy(UUID profileId, UUID studyId, StudyDto dto) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado"));

        Study study = studyRepository.findById(studyId)
                .orElseThrow(() -> new RuntimeException("Estudio no encontrado"));

        if (!study.getProfile().getId().equals(profileId)) {
            throw new RuntimeException("Este estudio no pertenece a ese perfil");
        }

        // Actualiza campos.
        study.setTitle(dto.getTitle());
        study.setInstitution(dto.getInstitution());
        study.setLevel(dto.getLevel());
        study.setStartDate(dto.getStartDate());
        study.setEndDate(dto.getEndDate());

        return studyMapper.toDto(studyRepository.save(study));
    }

    @Override
    public void deleteStudy(UUID profileId, UUID studyId) {
        Study study = studyRepository.findById(studyId)
                .orElseThrow(() -> new RuntimeException("Estudio no encontrado"));

        if (!study.getProfile().getId().equals(profileId)) {
            throw new RuntimeException("Este estudio no pertenece a ese perfil");
        }
        studyRepository.deleteById(studyId);
    }
}
