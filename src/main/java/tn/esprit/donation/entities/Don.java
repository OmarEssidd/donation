package tn.esprit.donation.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor  // Keep this annotation for potential no-argument constructor
public class Don implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDon; // Unique identifier for the donation

    private double montant; // Amount of the donation

    @Temporal(TemporalType.DATE)
    private Date date; // Date of the donation

    @Enumerated(EnumType.STRING)
    private TypeDons type; // Type of the donation (ESPECES, NATURE, etc.)

    @ManyToOne
    private Employe employe; // Association to the employee making the donation

    // Constructor with parameters for creating a Don object
    public Don(double montant, Date date, TypeDons type) {
        this.montant = montant;
        this.date = date;
        this.type = type;
    }

    // Constructor with parameters for creating a Don object with Employe
    public Don(Employe employe) {
        this.employe = employe;
    }
}
