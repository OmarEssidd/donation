package tn.esprit.donation.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.donation.entities.Employe;

import java.util.Date;
import java.util.List;

public interface EmployeRepository extends JpaRepository<Employe,Long> {

    @Query("select e from Employe e join Don d on d.employe =e join CroissantRouge  cr on d member cr.dons where cr.region = :region and e.entreprise.nomEntreprise = :nomentreprise")
            public List<Employe> getEmployeByRegionAndEntreprise(@Param("region") String region, @Param("nomentreprise") String nomentreprise) ;


}
