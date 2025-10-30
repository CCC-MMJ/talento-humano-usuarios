package co.edu.unimagdalena.devops.auth.service;

import co.edu.unimagdalena.devops.auth.dto.OfferDto;
import co.edu.unimagdalena.devops.auth.dto.ProfileDto;

import java.util.List;
import java.util.UUID;

public interface OfferService {
    OfferDto CreateOffer(OfferDto offerDto);
    OfferDto GetOffer(UUID offerId);
    List<OfferDto> GetAllOffers();
    OfferDto UpdateOffer(UUID id, OfferDto offerDto);
}
