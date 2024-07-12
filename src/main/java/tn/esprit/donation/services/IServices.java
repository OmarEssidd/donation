package tn.esprit.donation.services;

import org.springframework.data.repository.query.Param;
import tn.esprit.donation.entities.Don;
import tn.esprit.donation.entities.Employe;
import tn.esprit.donation.entities.Entreprise;
import tn.esprit.donation.entities.TypeDons;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface IServices {

    public Entreprise addEntreprise(Entreprise entreprise);
    public Employe addEmployeAndAssignToEntreprise(Employe employe, String nomEntreprise);

    public Don addDon(Don don);
    public Set<Don> getDonByType(TypeDons type) ;

    public void getEmployeByDon() ;

    public List<Employe> getEmployeByRegion(String region, String nomentreprise) ;

    public Float getTotalDonation(Date date1, Date date2) ;

}
