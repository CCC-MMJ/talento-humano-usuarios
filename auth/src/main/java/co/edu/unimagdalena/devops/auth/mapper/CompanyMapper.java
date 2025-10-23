package co.edu.unimagdalena.devops.auth.mapper;


import co.edu.unimagdalena.devops.auth.dto.CompanyDto;
import co.edu.unimagdalena.devops.auth.entity.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyDto toDto(Company company);
    Company toEntity(CompanyDto companyDto);
}
