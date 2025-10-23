package co.edu.unimagdalena.devops.auth.mapper;

import co.edu.unimagdalena.devops.auth.dto.CertificationDto;
import co.edu.unimagdalena.devops.auth.entity.Certification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CertificationMapper {
    CertificationDto toDto(Certification certification);
    Certification toEntity(CertificationDto certificationDto);
}