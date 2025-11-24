package co.edu.unimagdalena.devops.auth.service.imp;

import co.edu.unimagdalena.devops.auth.dto.DepartamentDto;
import co.edu.unimagdalena.devops.auth.entity.Departament;
import co.edu.unimagdalena.devops.auth.entity.Skill;
import co.edu.unimagdalena.devops.auth.mapper.DepartamentMapper;
import co.edu.unimagdalena.devops.auth.repository.DepartamentRepository;
import co.edu.unimagdalena.devops.auth.service.DepartamentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class DepartamentServiceImp implements DepartamentService {

    private final DepartamentRepository departamentRepository;
    private final DepartamentMapper departamentMapper;


    @Override
    @jakarta.transaction.Transactional
    public void seed(String name) {
        if (!departamentRepository.existsByName(name)) {
            departamentRepository.save(new Departament(null, name));
        }
    }

    @Override
    public DepartamentDto createDepartament(DepartamentDto departamentDto) {
        Departament entity = departamentMapper.toEntity(departamentDto);
        return departamentMapper.toDto(departamentRepository.save(entity));
    }

    @Override
    public DepartamentDto updateDepartament(UUID id, DepartamentDto departamentDto) {
        Departament existing = departamentRepository.findById(id).orElseThrow(() -> new RuntimeException("Departament not found!"));
        // map fields
        existing.setName(departamentDto.getName());
        return departamentMapper.toDto(departamentRepository.save(existing));
    }

    @Override
    public void deleteDepartament(UUID id) {
        departamentRepository.deleteById(id);
    }

    @Override
    public DepartamentDto getDepartamentById(UUID id) {
        return departamentMapper.toDto(departamentRepository.findById(id).orElseThrow(() -> new RuntimeException("Departament not found!")));
    }

    @Override
    public Optional<DepartamentDto> getDepartamentByName(String name) {
        return departamentRepository.findByNameIgnoreCase(name).map(departamentMapper::toDto);
    }

    @Override
    public List<DepartamentDto> getAllDepartaments() {
        return departamentRepository.findAll().stream().map(departamentMapper::toDto).toList();
    }
}

