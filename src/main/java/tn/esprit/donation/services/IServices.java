package tn.esprit.donation.services;

import tn.esprit.donation.entities.Don;
import tn.esprit.donation.entities.Employe;
import tn.esprit.donation.entities.Entreprise;
import tn.esprit.donation.entities.TypeDons;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface IServices {

    Entreprise addEntreprise(Entreprise entreprise);

    Employe addEmployeAndAssignToEntreprise(Employe employe, String nomEntreprise);

    Don addDon(Don don);

    Set<Don> getDonByType(TypeDons type);

    void getEmployeByDon();

    List<Employe> getEmployeByRegion(String region, String nomentreprise);

    Float getTotalDonation(Date date1, Date date2);
}
