package co.edu.unimagdalena.devops.auth.mapper;

import co.edu.unimagdalena.devops.auth.dto.DepartamentDto;
import co.edu.unimagdalena.devops.auth.entity.Departament;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartamentMapper {
    DepartamentDto toDto(Departament departament);
    Departament toEntity(DepartamentDto dto);
}

