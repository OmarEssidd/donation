package tn.esprit.donation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.donation.entities.Don;
import tn.esprit.donation.entities.TypeDons;

import java.util.Date;
import java.util.Set;

public interface DonRepository extends JpaRepository<Don, Long> {
    
    Set<Don> findByType(TypeDons type);
    
    /**
     * Finds all donations made in the current month.
     * 
     * @return a set of donations made in the current month
     */
    @Query("SELECT d FROM Don d WHERE MONTH(d.date) = MONTH(CURRENT_DATE) AND YEAR(d.date) = YEAR(CURRENT_DATE)")
    Set<Don> findDonByMonth();

    /**
     * Calculates the total donation amount between two dates.
     *
     * @param dateDebut the start date
     * @param dateFin the end date
     * @return the total donation amount as a Float
     */
    @Query("SELECT SUM(d.montant) FROM Don d WHERE d.date BETWEEN :dateDebut AND :dateFin")
    Float calculateTotalDonationsBetweenDates(@Param("dateDebut") Date dateDebut, @Param("dateFin") Date dateFin);
}
