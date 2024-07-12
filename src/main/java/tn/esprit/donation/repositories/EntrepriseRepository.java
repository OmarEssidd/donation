package tn.esprit.donation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.donation.entities.Entreprise;

public interface EntrepriseRepository extends JpaRepository<Entreprise,Long> {

    public Entreprise findByNomEntreprise(String nomEntreprise);
}
