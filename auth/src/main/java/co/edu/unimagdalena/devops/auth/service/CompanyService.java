package co.edu.unimagdalena.devops.auth.service;

import co.edu.unimagdalena.devops.auth.dto.CompanyDto;

import java.util.List;
import java.util.UUID;

public interface CompanyService {
    CompanyDto createCompany(CompanyDto companyDto);
    CompanyDto updateCompany(CompanyDto companyDto);
    void deleteCompany(UUID id);
    CompanyDto getCompanyById(UUID id);
    List<CompanyDto> getAllCompanies();
}
