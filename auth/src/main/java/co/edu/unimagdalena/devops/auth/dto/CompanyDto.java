package co.edu.unimagdalena.devops.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {
    private UUID id;
    private String name;
    private String description;
    private String sector;
    private String nit;
    private String email;
    private String numberPhone;
}

