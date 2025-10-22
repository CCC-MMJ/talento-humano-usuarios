package co.edu.unimagdalena.devops.auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "profile")
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column
    private String educationLevel;

    @Column
    private String locality;

    @Column
    private String phoneNumber;

    @Column
    private String expectations;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "profile")
    private List<Study> studies;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "profile")
    private List<Certification> certifications;

    @OneToMany(mappedBy = "profile")
    private List<Experiencie> experiencies;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "profile")
    @JoinTable(name = "profile_skill",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private List<Skill> skills;
}
