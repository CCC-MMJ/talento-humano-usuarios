package co.edu.unimagdalena.devops.auth.mapper;

import co.edu.unimagdalena.devops.auth.dto.PostulationDto;
import co.edu.unimagdalena.devops.auth.entity.Postulation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostulationMapper {
    PostulationDto toDto(Postulation postulation);
    Postulation toEntity(PostulationDto postulationDto);
}
