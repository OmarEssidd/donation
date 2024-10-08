package tn.esprit.donation.services;

import tn.esprit.donation.entities.Don;
import tn.esprit.donation.entities.Employe;
import tn.esprit.donation.entities.Entreprise;
import tn.esprit.donation.entities.TypeDons;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IServices {

    public Entreprise addEntreprise(Entreprise entreprise);
    public Employe addEmployeAndAssignToEntreprise(Employe employe, String nomEntreprise);
    public Don addDon(Don don);
    public Set<Don> getDonByType(TypeDons type);
    public List<Employe> getEmployeByDon();
    public List<Employe> getEmployeByRegion(String region, String nomentreprise);
    public Float getTotalDonation(Date date1, Date date2);
    public Optional<Don> getDonById(Long id); // Added method to get donation by ID
    public Don createDon(Don don); // Added method to create donation
}
