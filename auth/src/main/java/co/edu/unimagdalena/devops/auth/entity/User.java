package co.edu.unimagdalena.devops.auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "app_user")
@AllArgsConstructor
@NoArgsConstructor
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        UUID id;

        @Column
        String name;

        @Column
        String address;

        @Column
        String email;

        @Column
        String password;

        @Enumerated(EnumType.STRING)
        @Column(name = "user_type")
        private UserType userType;

        @ManyToMany
        @JoinTable(name = "user_company", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "company_id"))
        private List<Company> companies;
}
