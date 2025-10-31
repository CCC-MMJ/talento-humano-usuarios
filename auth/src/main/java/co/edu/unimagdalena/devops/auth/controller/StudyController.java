package co.edu.unimagdalena.devops.auth.controller;

import co.edu.unimagdalena.devops.auth.dto.StudyDto;
import co.edu.unimagdalena.devops.auth.service.StudyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profiles/{profileId}/studies")
@Tag(name = "Studies", description = "Operations related to profile studies")
public class StudyController {
    @Autowired
    private StudyService studyService;

    // Agregar estudio
    @PostMapping
    @Operation(summary = "Add study", description = "Add a new study to a profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Study added successfully", content = @Content(schema = @Schema(implementation = StudyDto.class))),
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content),
            @ApiResponse(responseCode = "404", description = "Profile not found", content = @Content)
    })
    public StudyDto addStudy(
            @PathVariable UUID profileId,
            @RequestBody StudyDto dto) {
        return studyService.addStudy(profileId, dto);
    }

    // Actualizar estudio puntual
    @PutMapping("/{studyId}")
    @Operation(summary = "Update study", description = "Update an existing study of a profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Study updated successfully", content = @Content(schema = @Schema(implementation = StudyDto.class))),
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content),
            @ApiResponse(responseCode = "404", description = "Profile or study not found", content = @Content)
    })
    public StudyDto updateStudy(
            @PathVariable UUID profileId,
            @PathVariable UUID studyId,
            @RequestBody StudyDto dto) {
        return studyService.updateStudy(profileId, studyId, dto);
    }

    // Eliminar estudio puntual
    @DeleteMapping("/{studyId}")
    @Operation(summary = "Delete study", description = "Delete a study from a profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Study deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Profile or study not found", content = @Content)
    })
    public void deleteStudy(
            @PathVariable UUID profileId,
            @PathVariable UUID studyId) {
        studyService.deleteStudy(profileId, studyId);
    }
}
