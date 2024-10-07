package tn.esprit.donation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Don implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDon;

    private double montant;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Enumerated(EnumType.STRING)
    private TypeDons type;

    @ManyToOne
    @JsonIgnore  // Empêche les problèmes de sérialisation JSON si nécessaire
    private Employe employe;

    // Méthodes manuelles pour l'employé au cas où Lombok ne fonctionne pas
    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
}
