package co.edu.unimagdalena.devops.auth.controller;

import co.edu.unimagdalena.devops.auth.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/study")
public class StudyController {
    private final StudyService studyService;


}
