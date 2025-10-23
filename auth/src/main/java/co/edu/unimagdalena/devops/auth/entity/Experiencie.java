package co.edu.unimagdalena.devops.auth.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Experiencie {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name="perfil_id")
    private Profile profile;

    private String companyName;
    private String charge;
    private String description;
    private LocalDate creationDate;
    private LocalDate expirationDate;




}
