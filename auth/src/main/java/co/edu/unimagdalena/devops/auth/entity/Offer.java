package co.edu.unimagdalena.devops.auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "offer")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    Company company;

    @OneToMany(mappedBy = "offer")
    List<Postulation> postulations;

    String title;
    String description;
    Etype type;
    String requirements;
    Date publicationDate;;
    Date ClosingDate;
    String status;









}
