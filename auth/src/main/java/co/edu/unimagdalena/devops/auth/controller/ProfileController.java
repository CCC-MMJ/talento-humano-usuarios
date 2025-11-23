package co.edu.unimagdalena.devops.auth.controller;

import co.edu.unimagdalena.devops.auth.dto.ProfileDto;
import co.edu.unimagdalena.devops.auth.security.JwtIssuer;
import co.edu.unimagdalena.devops.auth.service.ProfileService;
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
@RequestMapping("api/v1/profile")
@Tag(name = "Profiles", description = "Operations related to profiles management")
public class ProfileController {

    private final ProfileService profileService;
    private final JwtIssuer jwtIssuer;

    @GetMapping("/all")
    @Operation(summary = "Get all profiles", description = "Retrieve a list of all profiles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of profiles returned", content = @Content(schema = @Schema(implementation = ProfileDto.class)))
    })
    public ResponseEntity<List<ProfileDto>> getAllProfiles() {
        return ResponseEntity.ok(profileService.getAllProfiles());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get profile by ID", description = "Retrieve a profile by its unique identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile found", content = @Content(schema = @Schema(implementation = ProfileDto.class))),
            @ApiResponse(responseCode = "404", description = "Profile not found", content = @Content)
    })
    public ResponseEntity<ProfileDto> getProfileById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(profileService.getProfileById(id).orElseThrow());
    }

    @GetMapping("/skill")
    @Operation(summary = "Get profiles by skills", description = "Retrieve profiles that match the given list of skill names; pass multiple names as query params, e.g. ?names=Java&names=Python")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profiles found", content = @Content(schema = @Schema(implementation = ProfileDto.class)))
    })
    public ResponseEntity<List<ProfileDto>> getProfilesBySkills(@RequestParam("names") List<String> skillNames) {
        return ResponseEntity.ok(profileService.getProfilesBySkills(skillNames));
    }

    @PostMapping("/create")
    @Operation(summary = "Create profile", description = "Create a new profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile created successfully", content = @Content(schema = @Schema(implementation = ProfileDto.class))),
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content)
    })
    public ResponseEntity<ProfileDto> createProfile(@RequestBody ProfileDto profileDto) {
        return ResponseEntity.ok(profileService.createProfile(profileDto));
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update profile", description = "Update an existing profile by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile updated successfully", content = @Content(schema = @Schema(implementation = ProfileDto.class))),
            @ApiResponse(responseCode = "400", description = "Validation error", content = @Content),
            @ApiResponse(responseCode = "404", description = "Profile not found", content = @Content)
    })
    public ResponseEntity<ProfileDto> updateProfile(@PathVariable UUID id, @RequestBody ProfileDto profileDto) {
        return ResponseEntity.ok(profileService.updateProfile(profileDto, id));
    }
}