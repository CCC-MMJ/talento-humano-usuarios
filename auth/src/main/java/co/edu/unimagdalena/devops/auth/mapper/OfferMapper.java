package co.edu.unimagdalena.devops.auth.mapper;

import co.edu.unimagdalena.devops.auth.dto.OfferDto;
import co.edu.unimagdalena.devops.auth.entity.Offer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OfferMapper {
    Offer toDto(Offer offer);
    Offer toEntity(OfferDto offerDto);
}
