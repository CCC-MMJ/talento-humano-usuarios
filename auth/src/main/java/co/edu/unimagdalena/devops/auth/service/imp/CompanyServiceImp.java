package co.edu.unimagdalena.devops.auth.service.imp;

import co.edu.unimagdalena.devops.auth.dto.CompanyDto;
import co.edu.unimagdalena.devops.auth.entity.Company;
import co.edu.unimagdalena.devops.auth.mapper.CompanyMapper;
import co.edu.unimagdalena.devops.auth.repository.CompanyRepository;
import co.edu.unimagdalena.devops.auth.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CompanyServiceImp implements CompanyService {

    private CompanyRepository companyRepository;
    private CompanyMapper companyMapper;

    @Override
    public CompanyDto createCompany(CompanyDto companyDto) {


        Company company = companyMapper.toEntity(companyDto);

        return companyMapper.toDto( companyRepository.save(company));
    }

    @Override
    public CompanyDto updateCompany(UUID id, CompanyDto companyDto) {
        return null;
    }

    @Override
    public void deleteCompany(UUID id) {
        companyRepository.deleteById(id);
    }

    @Override
    public CompanyDto getCompanyById(UUID id) {
        return  companyMapper.toDto( companyRepository.findById(id).orElseThrow(() -> new RuntimeException("Company not found!")));
    }

    @Override
    public List<CompanyDto> getAllCompanies() {
        return companyRepository.findAll().stream().map(companyMapper::toDto).toList();
    }
}
