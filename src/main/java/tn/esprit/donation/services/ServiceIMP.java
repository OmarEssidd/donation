package tn.esprit.donation.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.donation.entities.Don;
import tn.esprit.donation.entities.Employe;
import tn.esprit.donation.entities.Entreprise;
import tn.esprit.donation.entities.TypeDons;
import tn.esprit.donation.repositories.CroissantRougeRepository;
import tn.esprit.donation.repositories.DonRepository;
import tn.esprit.donation.repositories.EmployeRepository;
import tn.esprit.donation.repositories.EntrepriseRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ServiceIMP  implements IServices {

    private EntrepriseRepository entrepriseRepository;
    private EmployeRepository employeRepository;
    private DonRepository donRepository;
    private CroissantRougeRepository croissantRougeRepository;

    @Override
    public Entreprise addEntreprise(Entreprise entreprise) {
        return entrepriseRepository.save(entreprise);
    }

    @Override
    public Employe addEmployeAndAssignToEntreprise(Employe employe, String nomEntreprise) {
        Entreprise entreprise = entrepriseRepository.findByNomEntreprise(nomEntreprise);
        employe.setEntreprise(entreprise);
        return employeRepository.save(employe);
    }

    @Override
    public Don addDon(Don don) {

        return donRepository.save(don);

    }

    @Override
    public Set<Don> getDonByType(TypeDons type) {
        return donRepository.findByType(type);
    }

    @Override
  @Scheduled(cron = "*/15 * * * * *")
  //  @Scheduled(cron = "0 0 0 1 * *")
    public void getEmployeByDon() {

       List<Don> currentMonthDonations = donRepository.findDonByMonth();


        Map<Employe, Long> donationCountsByEmployee = currentMonthDonations.stream()
                .collect(Collectors.groupingBy(Don::getEmploye, Collectors.counting()));


        Employe bestEmployee = donationCountsByEmployee.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

       if (bestEmployee != null) {
            log.info("Le meilleur employé du mois est : " + bestEmployee.getNomEmploye() );
       }
    }

    @Override
    public List<Employe> getEmployeByRegion(String region, String nomentreprise) {
        return croissantRougeRepository.getEmployeByRegion(region, nomentreprise);
    }

    @Override
    public Float getTotalDonation(Date date1, Date date2) {
        return donRepository.getTotalByDon(date1, date2);
    }
}
