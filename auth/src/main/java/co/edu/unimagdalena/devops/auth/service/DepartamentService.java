package co.edu.unimagdalena.devops.auth.service;

import co.edu.unimagdalena.devops.auth.dto.DepartamentDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DepartamentService {
    DepartamentDto createDepartament(DepartamentDto departamentDto);
    DepartamentDto updateDepartament(UUID id, DepartamentDto departamentDto);
    void deleteDepartament(UUID id);
    DepartamentDto getDepartamentById(UUID id);
    Optional<DepartamentDto> getDepartamentByName(String name);
    List<DepartamentDto> getAllDepartaments();
    void seed(String name);
}

