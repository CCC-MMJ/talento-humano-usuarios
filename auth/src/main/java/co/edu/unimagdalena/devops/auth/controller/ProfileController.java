package co.edu.unimagdalena.devops.auth.controller;

import co.edu.unimagdalena.devops.auth.dto.ProfileDto;
import co.edu.unimagdalena.devops.auth.dto.SkillDto;
import co.edu.unimagdalena.devops.auth.security.JwtIssuer;
import co.edu.unimagdalena.devops.auth.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/profile")
public class ProfileController {

    private final ProfileService profileService;
    private final JwtIssuer jwtIssuer;

    @GetMapping("/all")
    public ResponseEntity<List<ProfileDto>> getAllProfiles() {
        return ResponseEntity.ok(profileService.getAllProfiles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDto> getProfileById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(profileService.getProfileById(id).orElseThrow());
    }

    @GetMapping("/{listSkills}")
    public ResponseEntity<List<ProfileDto>> getProfilesBySkills(@PathVariable("listSkills") List<SkillDto> listSkills) {
        return ResponseEntity.ok(profileService.getProfilesBySkills(listSkills));
    }

    @PostMapping("/create")
    public ResponseEntity<ProfileDto> createProfile(@RequestBody ProfileDto profileDto) {
        return ResponseEntity.ok(profileService.createProfile(profileDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProfileDto> updateProfile(@PathVariable UUID id, @RequestBody ProfileDto profileDto) {
        return ResponseEntity.ok(profileService.updateProfile(profileDto, id));
    }
}