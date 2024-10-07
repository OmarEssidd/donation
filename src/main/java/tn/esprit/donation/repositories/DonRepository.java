package tn.esprit.donation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.donation.entities.Don;

import java.util.Date;
import java.util.Set;

public interface DonRepository extends JpaRepository<Don, Long> {
    
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
     * @param date1 the start date
     * @param date2 the end date
     * @return the total donation amount as a Float
     */
    @Query("SELECT SUM(d.montant) FROM Don d WHERE d.date BETWEEN :date1 AND :date2")
    Float getTotalByDon(@Param("date1") Date date1, @Param("date2") Date date2);
}
