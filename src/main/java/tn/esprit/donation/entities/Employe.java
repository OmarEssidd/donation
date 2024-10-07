package tn.esprit.donation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmploye;

    private String nomEmploye;

    private String prenomEmploye;

    private String poste;

    @ManyToOne
    @JsonIgnore
    private Entreprise entreprise;

    @OneToMany(mappedBy = "employe")
    @JsonIgnore
    private Set<Don> dons;

    // Manually added getter in case Lombok isn't recognized
    public String getNomEmploye() {
        return nomEmploye;
    }

    // Manually added setEntreprise method
    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    // Manually added getEntreprise method
    public Entreprise getEntreprise() {
        return entreprise;
    }

    // Optional getter for dons in case Lombok isn't recognized
    public Set<Don> getDons() {
        return dons;
    }
}
