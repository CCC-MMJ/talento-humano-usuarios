package co.edu.unimagdalena.devops.auth.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "postulation")
@Data

public class Postulation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "offer_id")
    Offer offer;

    private LocalDate creationDate;
    private String status;



}
