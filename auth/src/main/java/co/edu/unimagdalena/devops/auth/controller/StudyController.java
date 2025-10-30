package co.edu.unimagdalena.devops.auth.controller;

import co.edu.unimagdalena.devops.auth.dto.StudyDto;
import co.edu.unimagdalena.devops.auth.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profiles/{profileId}/studies")
public class StudyController {
    @Autowired
    private StudyService studyService;

    // Agregar estudio
    @PostMapping
    public StudyDto addStudy(
            @PathVariable UUID profileId,
            @RequestBody StudyDto dto) {
        return studyService.addStudy(profileId, dto);
    }

    // Actualizar estudio puntual
    @PutMapping("/{studyId}")
    public StudyDto updateStudy(
            @PathVariable UUID profileId,
            @PathVariable UUID studyId,
            @RequestBody StudyDto dto) {
        return studyService.updateStudy(profileId, studyId, dto);
    }

    // Eliminar estudio puntual
    @DeleteMapping("/{studyId}")
    public void deleteStudy(
            @PathVariable UUID profileId,
            @PathVariable UUID studyId) {
        studyService.deleteStudy(profileId, studyId);
    }
}
