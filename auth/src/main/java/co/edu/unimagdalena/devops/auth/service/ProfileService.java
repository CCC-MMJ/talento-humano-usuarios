package co.edu.unimagdalena.devops.auth.service;

import co.edu.unimagdalena.devops.auth.dto.ProfileDto;
import co.edu.unimagdalena.devops.auth.dto.SkillDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfileService {
    List<ProfileDto> getAllProfiles();
    Optional<ProfileDto> getProfileById(UUID id);
    List<ProfileDto> getProfilesBySkills(List<SkillDto> listSkills);
    ProfileDto createProfile(ProfileDto profileDto);
    ProfileDto updateProfile(ProfileDto profileDto, UUID id);
    void deleteProfileById(UUID id);
}