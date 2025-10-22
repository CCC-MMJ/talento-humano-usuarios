package co.edu.unimagdalena.devops.auth.service.imp;

import co.edu.unimagdalena.devops.auth.dto.ProfileDto;
import co.edu.unimagdalena.devops.auth.mapper.ProfileMapper;
import co.edu.unimagdalena.devops.auth.repository.ProfileRepository;
import co.edu.unimagdalena.devops.auth.service.ProfileService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProfileServiceImp implements ProfileService {
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    public ProfileServiceImp(ProfileRepository profileRepository, ProfileMapper profileMapper) {
        this.profileRepository = profileRepository;
        this.profileMapper = profileMapper;
    }

    @Override
    public List<ProfileDto> getAllProfiles() {
        return profileRepository.findAll().stream().map(profileMapper::toDto).toList();
    }

    @Override
    public Optional<ProfileDto> getProfileById(UUID id) {
        return profileRepository.findById(id).map(profileMapper::toDto);
    }

    @Override
    public ProfileDto createProfile(ProfileDto profileDto) {
        return null;
    }

    @Override
    public void deleteProfileById(UUID id) {
        profileRepository.deleteById(id);
    }
}