package co.edu.unimagdalena.devops.auth.controller;

import co.edu.unimagdalena.devops.auth.dto.SkillDto;
import co.edu.unimagdalena.devops.auth.security.JwtIssuer;
import co.edu.unimagdalena.devops.auth.service.SkillSeedService;
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
@RequestMapping("api/v1/skill")
@Tag(name = "Skills", description = "Operations related to skills management")
public class SkillController {

	private final SkillSeedService skillService;
	private final JwtIssuer jwtIssuer;

	@GetMapping("/all")
	@Operation(summary = "Get all skills", description = "Retrieve a list of all skills")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "List of skills returned", content = @Content(schema = @Schema(implementation = SkillDto.class)))
	})
	public ResponseEntity<List<SkillDto>> getAllSkills() {
		return ResponseEntity.ok(skillService.getAllSkills());
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get skill by ID", description = "Retrieve a skill by its unique identifier")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Skill found", content = @Content(schema = @Schema(implementation = SkillDto.class))),
			@ApiResponse(responseCode = "404", description = "Skill not found", content = @Content)
	})
	public ResponseEntity<SkillDto> getSkillById(@PathVariable("id") UUID id) {
		return ResponseEntity.ok(skillService.getSkillById(id).orElseThrow());
	}

	@PostMapping("/create")
	@Operation(summary = "Create skill", description = "Create a new skill")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Skill created successfully", content = @Content(schema = @Schema(implementation = SkillDto.class))),
			@ApiResponse(responseCode = "400", description = "Validation error", content = @Content)
	})
	public ResponseEntity<SkillDto> createSkill(@RequestBody SkillDto skillDto) {
		return ResponseEntity.ok(skillService.createSkill(skillDto));
	}

	@PutMapping("/update/{id}")
	@Operation(summary = "Update skill", description = "Update an existing skill by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Skill updated successfully", content = @Content(schema = @Schema(implementation = SkillDto.class))),
			@ApiResponse(responseCode = "400", description = "Validation error", content = @Content),
			@ApiResponse(responseCode = "404", description = "Skill not found", content = @Content)
	})
	public ResponseEntity<SkillDto> updateSkill(@PathVariable UUID id, @RequestBody SkillDto skillDto) {
		return ResponseEntity.ok(skillService.updateSkill(skillDto, id));
	}
}
