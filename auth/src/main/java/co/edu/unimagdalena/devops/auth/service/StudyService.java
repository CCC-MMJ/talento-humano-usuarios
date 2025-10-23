package co.edu.unimagdalena.devops.auth.service;

import co.edu.unimagdalena.devops.auth.dto.StudyDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudyService {
    List<StudyDto> getAllStudies();
    Optional<StudyDto> getStudyById(UUID id);
    StudyDto createStudy(StudyDto studyDto);
    void deleteStudyById(UUID id);
}
