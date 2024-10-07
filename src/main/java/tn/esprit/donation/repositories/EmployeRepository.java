package tn.esprit.donation.repositories;

import tn.esprit.donation.entities.Employe;
import tn.esprit.donation.entities.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long> {
    List<Employe> getEmployeByRegionAndEntreprise(String region, String nomEntreprise);
    Entreprise findByNomEntreprise(String nomEntreprise);
}
