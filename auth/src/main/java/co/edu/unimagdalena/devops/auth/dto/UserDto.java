package co.edu.unimagdalena.devops.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Represents a user in the system")
public class UserDto {

    @Schema(description = "Unique identifier of the user", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private UUID id;
    @Schema(description = "Full name of the user", example = "Jane Doe")
    private String name;
    @Schema(description = "Home address", example = "123 Main St, Springfield")
    private String address;
    @Schema(description = "Email address", example = "jane.doe@example.com")
    private String email;
    @Schema(description = "Type of user account", example = "applicant", allowableValues = { "applicant", "company",
            "institution" })
    private String type;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
