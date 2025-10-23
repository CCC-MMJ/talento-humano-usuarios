package co.edu.unimagdalena.devops.auth.service.imp;

import co.edu.unimagdalena.devops.auth.dto.StudyDto;
import co.edu.unimagdalena.devops.auth.mapper.StudyMapper;
import co.edu.unimagdalena.devops.auth.repository.StudyRepository;
import co.edu.unimagdalena.devops.auth.service.StudyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudyServiceImp implements StudyService {
    private final StudyRepository studyRepository;
    private final StudyMapper studyMapper;

    public StudyServiceImp(StudyRepository studyRepository, StudyMapper studyMapper) {
        this.studyRepository = studyRepository;
        this.studyMapper = studyMapper;
    }


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
}
