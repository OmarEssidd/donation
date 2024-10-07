package tn.esprit.donation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.donation.entities.Don;
import tn.esprit.donation.entities.TypeDons;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface DonRepository extends JpaRepository<Don,Long> {

    public Set<Don> findByType(TypeDons type) ;

    @Query("SELECT d FROM Don d WHERE MONTH(d.date) = MONTH(CURRENT_DATE) AND YEAR(d.date) = YEAR(CURRENT_DATE)")
    public List<Don> findDonByMonth();
@Query("select SUM(d.montant) from Don d where d.date BETWEEN :date1 and :date2")
    public Float getTotalByDon(@Param("date1") Date date1, @Param("date2") Date date2);

}
