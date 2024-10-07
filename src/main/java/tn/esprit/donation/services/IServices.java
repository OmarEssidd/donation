package tn.esprit.donation.services;

import tn.esprit.donation.entities.Don;
import tn.esprit.donation.entities.Employe;
import tn.esprit.donation.entities.Entreprise;
import tn.esprit.donation.entities.TypeDons;

import java.util.Date;
import java.util.List;

public interface IServices {

    Entreprise addEntreprise(Entreprise entreprise);

    Employe addEmployeAndAssignToEntreprise(Employe employe, String nomEntreprise);

    Don addDon(Don don);

    List<Don> getDonByType(TypeDons type); // Cette méthode retourne maintenant une List<Don>

    void getEmployeByDon();

    List<Employe> getEmployeByRegion(String region, String nomEntreprise); // Corrigez le nom de la variable à "nomEntreprise"

    Float getTotalDonation(Date date1, Date date2);
}
