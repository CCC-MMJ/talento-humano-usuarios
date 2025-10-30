package co.edu.unimagdalena.devops.auth.mapper;

import co.edu.unimagdalena.devops.auth.dto.OfferDto;
import co.edu.unimagdalena.devops.auth.entity.Offer;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OfferMapper {
    OfferDto toDto(Offer offer);
    Offer toEntity(OfferDto offerDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "company", ignore = true)
    void updateEntityFromDto(OfferDto dto, @MappingTarget Offer entity);

}
