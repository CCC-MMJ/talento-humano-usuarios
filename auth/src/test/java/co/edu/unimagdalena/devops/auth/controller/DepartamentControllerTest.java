package co.edu.unimagdalena.devops.auth.controller;

import co.edu.unimagdalena.devops.auth.dto.DepartamentDto;
import co.edu.unimagdalena.devops.auth.security.JwtIssuer;
import co.edu.unimagdalena.devops.auth.service.DepartamentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DepartamentControllerTest {

    @Mock
    private DepartamentService departamentService;

    @Mock
    private JwtIssuer jwtIssuer;

    @InjectMocks
    private DepartamentController departamentController;

    private UUID departamentId;
    private DepartamentDto testDepartamentDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        departamentId = UUID.randomUUID();

        testDepartamentDto = new DepartamentDto();
        testDepartamentDto.setId(departamentId);
        testDepartamentDto.setName("Engineering");
    }

    @Test
    void testGetAllDepartaments_Success() {
        // Arrange
        List<DepartamentDto> departaments = new ArrayList<>();
        departaments.add(testDepartamentDto);

        when(departamentService.getAllDepartaments()).thenReturn(departaments);

        // Act
        ResponseEntity<List<DepartamentDto>> response = departamentController.getAllDepartaments();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("Engineering", response.getBody().get(0).getName());
        verify(departamentService).getAllDepartaments();
    }

    @Test
    void testGetDepartamentById_Success() {
        // Arrange
        when(departamentService.getDepartamentById(departamentId)).thenReturn(testDepartamentDto);

        // Act
        ResponseEntity<DepartamentDto> response = departamentController.getDepartamentById(departamentId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Engineering", response.getBody().getName());
        verify(departamentService).getDepartamentById(departamentId);
    }

    @Test
    void testGetDepartamentById_NotFound() {
        // Arrange
        UUID nonExistentId = UUID.randomUUID();
        when(departamentService.getDepartamentById(nonExistentId))
                .thenThrow(new RuntimeException("Departament not found!"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> departamentController.getDepartamentById(nonExistentId));
        verify(departamentService).getDepartamentById(nonExistentId);
    }

    @Test
    void testCreateDepartament_Success() {
        // Arrange
        when(departamentService.createDepartament(testDepartamentDto)).thenReturn(testDepartamentDto);

        // Act
        ResponseEntity<DepartamentDto> response = departamentController.createDepartament(testDepartamentDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Engineering", response.getBody().getName());
        verify(departamentService).createDepartament(testDepartamentDto);
    }

    @Test
    void testUpdateDepartament_Success() {
        // Arrange
        testDepartamentDto.setName("Marketing");
        when(departamentService.updateDepartament(departamentId, testDepartamentDto))
                .thenReturn(testDepartamentDto);

        // Act
        ResponseEntity<DepartamentDto> response = departamentController.updateDepartament(
                departamentId, testDepartamentDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Marketing", response.getBody().getName());
        verify(departamentService).updateDepartament(departamentId, testDepartamentDto);
    }

    @Test
    void testDeleteDepartament_Success() {
        // Act
        ResponseEntity<Void> response = departamentController.deleteDepartament(departamentId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(departamentService).deleteDepartament(departamentId);
    }

    @Test
    void testGetAllDepartaments_Empty() {
        // Arrange
        when(departamentService.getAllDepartaments()).thenReturn(new ArrayList<>());

        // Act
        ResponseEntity<List<DepartamentDto>> response = departamentController.getAllDepartaments();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(0, response.getBody().size());
    }

    @Test
    void testCreateMultipleDepartaments() {
        // Arrange
        DepartamentDto dept2 = new DepartamentDto();
        dept2.setId(UUID.randomUUID());
        dept2.setName("Sales");

        when(departamentService.createDepartament(testDepartamentDto)).thenReturn(testDepartamentDto);
        when(departamentService.createDepartament(dept2)).thenReturn(dept2);

        // Act
        ResponseEntity<DepartamentDto> response1 = departamentController.createDepartament(testDepartamentDto);
        ResponseEntity<DepartamentDto> response2 = departamentController.createDepartament(dept2);

        // Assert
        assertEquals(HttpStatus.OK, response1.getStatusCode());
        assertEquals(HttpStatus.OK, response2.getStatusCode());
        assertEquals("Engineering", response1.getBody().getName());
        assertEquals("Sales", response2.getBody().getName());
    }
}
