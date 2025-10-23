package co.edu.unimagdalena.devops.auth.mapper;

import co.edu.unimagdalena.devops.auth.dto.StudyDto;
import co.edu.unimagdalena.devops.auth.entity.Study;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudyMapper {
    StudyDto toDto(Study study);
    Study toEntity(StudyDto studyDto);
}