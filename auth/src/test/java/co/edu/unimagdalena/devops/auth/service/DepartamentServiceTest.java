package co.edu.unimagdalena.devops.auth.service;

import co.edu.unimagdalena.devops.auth.dto.DepartamentDto;
import co.edu.unimagdalena.devops.auth.entity.Departament;
import co.edu.unimagdalena.devops.auth.mapper.DepartamentMapper;
import co.edu.unimagdalena.devops.auth.repository.DepartamentRepository;
import co.edu.unimagdalena.devops.auth.service.imp.DepartamentServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartamentServiceTest {

    @Mock
    private DepartamentRepository departamentRepository;

    @Mock
    private DepartamentMapper departamentMapper;

    @InjectMocks
    private DepartamentServiceImp departamentService;

    private UUID departamentId;
    private Departament testDepartament;
    private DepartamentDto testDepartamentDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        departamentId = UUID.randomUUID();

        testDepartament = new Departament();
        testDepartament.setId(departamentId);
        testDepartament.setName("Engineering");

        testDepartamentDto = new DepartamentDto();
        testDepartamentDto.setId(departamentId);
        testDepartamentDto.setName("Engineering");
    }

    @Test
    void testCreateDepartament_Success() {
        // Arrange
        when(departamentMapper.toEntity(testDepartamentDto)).thenReturn(testDepartament);
        when(departamentRepository.save(testDepartament)).thenReturn(testDepartament);
        when(departamentMapper.toDto(testDepartament)).thenReturn(testDepartamentDto);

        // Act
        DepartamentDto result = departamentService.createDepartament(testDepartamentDto);

        // Assert
        assertNotNull(result);
        assertEquals("Engineering", result.getName());
        verify(departamentMapper).toEntity(testDepartamentDto);
        verify(departamentRepository).save(testDepartament);
    }

    @Test
    void testUpdateDepartament_Success() {
        // Arrange
        testDepartamentDto.setName("Marketing");
        testDepartament.setName("Marketing");

        when(departamentRepository.findById(departamentId)).thenReturn(Optional.of(testDepartament));
        when(departamentRepository.save(testDepartament)).thenReturn(testDepartament);
        when(departamentMapper.toDto(testDepartament)).thenReturn(testDepartamentDto);

        // Act
        DepartamentDto result = departamentService.updateDepartament(departamentId, testDepartamentDto);

        // Assert
        assertNotNull(result);
        assertEquals("Marketing", result.getName());
        verify(departamentRepository).findById(departamentId);
        verify(departamentRepository).save(testDepartament);
    }

    @Test
    void testUpdateDepartament_NotFound() {
        // Arrange
        UUID nonExistentId = UUID.randomUUID();
        when(departamentRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            departamentService.updateDepartament(nonExistentId, testDepartamentDto);
        });
        verify(departamentRepository).findById(nonExistentId);
    }

    @Test
    void testDeleteDepartament() {
        // Act
        departamentService.deleteDepartament(departamentId);

        // Assert
        verify(departamentRepository).deleteById(departamentId);
    }

    @Test
    void testGetDepartamentById_Success() {
        // Arrange
        when(departamentRepository.findById(departamentId)).thenReturn(Optional.of(testDepartament));
        when(departamentMapper.toDto(testDepartament)).thenReturn(testDepartamentDto);

        // Act
        DepartamentDto result = departamentService.getDepartamentById(departamentId);

        // Assert
        assertNotNull(result);
        assertEquals("Engineering", result.getName());
        verify(departamentRepository).findById(departamentId);
    }

    @Test
    void testGetDepartamentById_NotFound() {
        // Arrange
        UUID nonExistentId = UUID.randomUUID();
        when(departamentRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            departamentService.getDepartamentById(nonExistentId);
        });
    }

    @Test
    void testGetDepartamentByName_Success() {
        // Arrange
        when(departamentRepository.findByNameIgnoreCase("Engineering")).thenReturn(Optional.of(testDepartament));
        when(departamentMapper.toDto(testDepartament)).thenReturn(testDepartamentDto);

        // Act
        Optional<DepartamentDto> result = departamentService.getDepartamentByName("Engineering");

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Engineering", result.get().getName());
        verify(departamentRepository).findByNameIgnoreCase("Engineering");
    }

    @Test
    void testGetDepartamentByName_NotFound() {
        // Arrange
        when(departamentRepository.findByNameIgnoreCase("NonExistent")).thenReturn(Optional.empty());

        // Act
        Optional<DepartamentDto> result = departamentService.getDepartamentByName("NonExistent");

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void testGetAllDepartaments() {
        // Arrange
        List<Departament> departaments = new ArrayList<>();
        departaments.add(testDepartament);

        List<DepartamentDto> departamentDtos = new ArrayList<>();
        departamentDtos.add(testDepartamentDto);

        when(departamentRepository.findAll()).thenReturn(departaments);
        when(departamentMapper.toDto(testDepartament)).thenReturn(testDepartamentDto);

        // Act
        List<DepartamentDto> result = departamentService.getAllDepartaments();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Engineering", result.get(0).getName());
        verify(departamentRepository).findAll();
    }

    @Test
    void testSeed_NewDepartament() {
        // Arrange
        when(departamentRepository.existsByName("HR")).thenReturn(false);

        // Act
        departamentService.seed("HR");

        // Assert
        verify(departamentRepository).existsByName("HR");
        verify(departamentRepository).save(any(Departament.class));
    }

    @Test
    void testSeed_ExistingDepartament() {
        // Arrange
        when(departamentRepository.existsByName("Engineering")).thenReturn(true);

        // Act
        departamentService.seed("Engineering");

        // Assert
        verify(departamentRepository).existsByName("Engineering");
        verify(departamentRepository, never()).save(any(Departament.class));
    }
}
