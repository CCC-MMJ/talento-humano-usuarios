package co.edu.unimagdalena.devops.auth.service.imp;

import co.edu.unimagdalena.devops.auth.dto.OfferDto;
import co.edu.unimagdalena.devops.auth.entity.Company;
import co.edu.unimagdalena.devops.auth.entity.Offer;
import co.edu.unimagdalena.devops.auth.mapper.CompanyMapper;
import co.edu.unimagdalena.devops.auth.mapper.OfferMapper;
import co.edu.unimagdalena.devops.auth.repository.CompanyRepository;
import co.edu.unimagdalena.devops.auth.repository.OfferRepository;
import co.edu.unimagdalena.devops.auth.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor

public class OfferServiceImp implements OfferService {

    private final OfferRepository offerRepository;

    private final CompanyRepository companyRepository;

    private final OfferMapper offerMapper;

    private final CompanyMapper companyMapper;



    @Override
    public OfferDto CreateOffer(OfferDto offerDto) {

        Company company = companyRepository.findById(offerDto.getCompanyId()).orElseThrow();

        Offer offer = offerMapper.toEntity(offerDto);
        offer.setCompany(company);

        Offer savedOffer = offerRepository.save(offer);
        return offerMapper.toDto(savedOffer);
    }

    @Override
    //obtener un offer por su ID
    public OfferDto GetOffer(UUID offerId) {
        return offerMapper.toDto(offerRepository.findById(offerId).orElseThrow());
    }

    @Override
    public List<OfferDto> GetAllOffers() {
        return offerRepository
                .findAll()
                .stream()
                .map(offerMapper::toDto)
                .toList();
    }

    @Override
    public OfferDto UpdateOffer(UUID id, OfferDto offerDto) {
       Offer offer =  offerRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Offer not found"));

       if(offerDto.getCompanyId()!=null){
           Company company = companyRepository.findById(offerDto.getCompanyId()).orElseThrow(() -> new RuntimeException("Company not found"));
           offer.setCompany(company);
       }

       offerMapper.updateEntityFromDto(offerDto, offer);

       return offerMapper.toDto(offerRepository.save(offer));

    }


}
