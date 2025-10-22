package co.edu.unimagdalena.devops.auth.entity;


import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name= "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @OneToMany(mappedBy = "company")
    private List<Offer> Offers;

    @ManyToMany(mappedBy = "companies")
    private List<User> users;

    private String name;
    private String description;
    private String sector;
    private String nit;
    private String email;
    private String numberPhone;
    private String status;



}
