package co.edu.unimagdalena.devops.auth.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name= "departament")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Departament {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    UUID id;

    String name;

    String a;

}
