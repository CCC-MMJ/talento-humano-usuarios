package co.edu.unimagdalena.devops.auth.mapper;

import co.edu.unimagdalena.devops.auth.dto.ProfileDto;
import co.edu.unimagdalena.devops.auth.entity.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileDto toDto(Profile profile);
    Profile toEntity(ProfileDto profileDto);

    @Mapping(target = "studies", ignore = true)
    @Mapping(target = "certifications", ignore = true)
    @Mapping(target = "experiencies", ignore = true)
    void updateProfile(ProfileDto profileDto, @MappingTarget Profile profile);
}