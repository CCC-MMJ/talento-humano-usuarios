package co.edu.unimagdalena.devops.auth.mapper;

import co.edu.unimagdalena.devops.auth.dto.PostulationDto;
import co.edu.unimagdalena.devops.auth.entity.Postulation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostulationMapper {
    @Mapping(target = "userName", source = "user.name")
    @Mapping(target = "offerId", source = "offer.id")
    @Mapping(target = "offerDescription", source = "offer.description")
    @Mapping(target = "offerTitle", source = "offer.title")
    PostulationDto toDto(Postulation postulation);
    Postulation toEntity(PostulationDto postulationDto);
}
