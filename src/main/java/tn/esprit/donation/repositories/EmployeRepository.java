package tn.esprit.donation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.donation.entities.Employe;

import java.util.List;

public interface EmployeRepository extends JpaRepository<Employe, Long> {
    
    @Query("SELECT e FROM Employe e JOIN e.dons d JOIN d.croissantRouge cr WHERE cr.region = :region AND e.entreprise.nomEntreprise = :nomentreprise")
    List<Employe> getEmployeByRegionAndEntreprise(@Param("region") String region, @Param("nomentreprise") String nomentreprise);
}
