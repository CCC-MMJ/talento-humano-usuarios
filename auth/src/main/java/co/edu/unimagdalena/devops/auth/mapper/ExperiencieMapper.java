package co.edu.unimagdalena.devops.auth.mapper;

import co.edu.unimagdalena.devops.auth.dto.ExperiencieDto;
import co.edu.unimagdalena.devops.auth.entity.Experiencie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExperiencieMapper {
    ExperiencieDto toDto(Experiencie experience);
    Experiencie toEntity(ExperiencieDto experiencieDto);
}
