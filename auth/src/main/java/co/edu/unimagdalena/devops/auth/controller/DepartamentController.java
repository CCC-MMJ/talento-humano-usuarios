package co.edu.unimagdalena.devops.auth.controller;

import co.edu.unimagdalena.devops.auth.dto.DepartamentDto;
import co.edu.unimagdalena.devops.auth.security.JwtIssuer;
import co.edu.unimagdalena.devops.auth.service.DepartamentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/departament")
@Tag(name = "Departaments", description = "Operations related to departaments management")
public class DepartamentController {

    private final DepartamentService departamentService;
    private final JwtIssuer jwtIssuer;

    @GetMapping("/all")
    @Operation(summary = "Get all departaments", description = "Retrieve a list of all departaments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of departaments returned", content = @Content(schema = @Schema(implementation = DepartamentDto.class)))
    })
    public ResponseEntity<List<DepartamentDto>> getAllDepartaments() {
        return ResponseEntity.ok(departamentService.getAllDepartaments());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get departament by ID", description = "Retrieve a departament by its unique identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Departament found", content = @Content(schema = @Schema(implementation = DepartamentDto.class))),
            @ApiResponse(responseCode = "404", description = "Departament not found", content = @Content)
    })
    public ResponseEntity<DepartamentDto> getDepartamentById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(departamentService.getDepartamentById(id));
    }

    @PostMapping("/create")
    @Operation(summary = "Create departament", description = "Create a new departament")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Departament created successfully", content = @Content(schema = @Schema(implementation = DepartamentDto.class))),
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content)
    })
    public ResponseEntity<DepartamentDto> createDepartament(@RequestBody DepartamentDto departamentDto) {
        return ResponseEntity.ok(departamentService.createDepartament(departamentDto));
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update departament", description = "Update an existing departament by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Departament updated successfully", content = @Content(schema = @Schema(implementation = DepartamentDto.class))),
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content),
            @ApiResponse(responseCode = "404", description = "Departament not found", content = @Content)
    })
    public ResponseEntity<DepartamentDto> updateDepartament(@PathVariable UUID id, @RequestBody DepartamentDto departamentDto) {
        return ResponseEntity.ok(departamentService.updateDepartament(id, departamentDto));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete departament", description = "Delete an existing departament by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Departament deleted successfully", content = @Content(schema = @Schema(implementation = DepartamentDto.class))),
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content),
            @ApiResponse(responseCode = "404", description = "Departament not found", content = @Content)
    })
    public ResponseEntity<Void> deleteDepartament(@PathVariable UUID id) {
        departamentService.deleteDepartament(id);
        return ResponseEntity.noContent().build();
    }
}

