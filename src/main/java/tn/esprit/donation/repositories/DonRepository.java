package tn.esprit.donation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.donation.entities.Don;
import tn.esprit.donation.entities.TypeDons;
import tn.esprit.donation.entities.Employe;


import java.util.Date;
import java.util.List;  // Ensure this import is present
import java.util.Set;

public interface DonRepository extends JpaRepository<Don, Long> {
    List<Don> findByEmploye(Employe employe);
    List<Don> findByType(TypeDons type);
    
    @Query("SELECT d FROM Don d WHERE MONTH(d.date) = MONTH(CURRENT_DATE) AND YEAR(d.date) = YEAR(CURRENT_DATE)")
    List<Don> findDonByMonth();

    @Query("SELECT SUM(d.montant) FROM Don d WHERE d.date BETWEEN :dateDebut AND :dateFin")
    Float calculateTotalDonationsBetweenDates(@Param("dateDebut") Date dateDebut, @Param("dateFin") Date dateFin);
}
