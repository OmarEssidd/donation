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

    // Added getter in case Lombok isn't recognized
    public String getNomEmploye() {
        return nomEmploye;
    }
}
