package co.edu.unimagdalena.devops.auth.service.imp;

import co.edu.unimagdalena.devops.auth.dto.CertificationDto;
import co.edu.unimagdalena.devops.auth.dto.ExperiencieDto;
import co.edu.unimagdalena.devops.auth.dto.ProfileDto;
import co.edu.unimagdalena.devops.auth.dto.StudyDto;
import co.edu.unimagdalena.devops.auth.entity.Certification;
import co.edu.unimagdalena.devops.auth.entity.Experiencie;
import co.edu.unimagdalena.devops.auth.entity.Profile;
import co.edu.unimagdalena.devops.auth.entity.Study;
import co.edu.unimagdalena.devops.auth.mapper.ProfileMapper;
import co.edu.unimagdalena.devops.auth.repository.ProfileRepository;
import co.edu.unimagdalena.devops.auth.repository.UserRepository;
import co.edu.unimagdalena.devops.auth.service.ProfileService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProfileServiceImp implements ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final ProfileMapper profileMapper;

    public ProfileServiceImp(ProfileRepository profileRepository, UserRepository userRepository, ProfileMapper profileMapper) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
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
        Profile profile = new Profile();

        profile.setUser(userRepository.findById(profileDto.getIdUser()).orElseThrow());
        profile.setEducationLevel(profileDto.getEducationalLevel());
        profile.setLocality(profileDto.getLocality());
        profile.setPhoneNumber(profileDto.getPhoneNumber());
        profile.setExpectations(profileDto.getExpectations());

        // Se Mapea los hijos (studies, certifications, experiences) del DTO a entidades
        List<Study> studies = new ArrayList<>();

        for (int i = 0; i < profileDto.getStudies().size(); i++) {
            Study study = new Study();

            study.setProfile(profile);
            study.setTitle(profile.getStudies().get(i).getTitle());
            study.setInstitution(profile.getStudies().get(i).getInstitution());
            study.setStartDate(profile.getStudies().get(i).getStartDate());
            study.setEndDate(profile.getStudies().get(i).getEndDate());
            study.setLevel(profile.getStudies().get(i).getLevel());

            studies.add(study);
        }
        profile.setStudies(studies);

        List<Certification> certifications = new ArrayList<>();

        for (int i = 0; i < profileDto.getCertifications().size(); i++) {
            Certification cert = new Certification();

            cert.setProfile(profile);
            cert.setName(profileDto.getCertifications().get(i).getName());
            cert.setEntity(profileDto.getCertifications().get(i).getEntity());
            cert.setDate(profileDto.getCertifications().get(i).getDate());

            certifications.add(cert);
        }
        profile.setCertifications(certifications);

        List<Experiencie> experiencies = new ArrayList<>();
        for (ExperiencieDto expDto : profileDto.getExperiencies()) {
            Experiencie exp = new Experiencie();

            exp.setProfile(profile);
            exp.setCompanyName(expDto.getCompanyName());
            exp.setCharge(expDto.getCharge());
            exp.setDescription(expDto.getDescription());
            exp.setCreationDate(expDto.getCreationDate());
            exp.setExpirationDate(expDto.getExpirationDate());

            experiencies.add(exp);
        }
        profile.setExperiencies(experiencies);

        // Los hijos se guardan automáticamente al guardar el perfil por el cascade
        return profileMapper.toDto(profileRepository.save(profile));
    }

    @Override
    public void deleteProfileById(UUID id) {
        profileRepository.deleteById(id);
    }
}