package tn.esprit.donation.services;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ServiceIMP implements IServices {

    private static final Logger log = LoggerFactory.getLogger(ServiceIMP.class);

    private final EntrepriseRepository entrepriseRepository;
    private final EmployeRepository employeRepository;
    private final DonRepository donRepository;
    private final CroissantRougeRepository croissantRougeRepository;

    @Override
    public Entreprise addEntreprise(Entreprise entreprise) {
        return entrepriseRepository.save(entreprise);
    }

    @Override
    public Employe addEmployeAndAssignToEntreprise(Employe employe, String nomEntreprise) {
        Entreprise entreprise = entrepriseRepository.findByNomEntreprise(nomEntreprise);
        if (entreprise == null) {
            throw new IllegalArgumentException("Entreprise non trouvée pour le nom : " + nomEntreprise);
        }
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
    public void getEmployeByDon() {
        Set<Don> currentMonthDonations = donRepository.findDonByMonth();

        Map<Employe, Long> donationCountsByEmployee = currentMonthDonations.stream()
                .collect(Collectors.groupingBy(Don::getEmploye, Collectors.counting()));

        Employe bestEmployee = donationCountsByEmployee.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        if (bestEmployee != null) {
            log.info("Le meilleur employé du mois est : " + bestEmployee.getNomEmploye());
        }
    }

    @Override
    public List<Employe> getEmployeByRegion(String region, String nomEntreprise) {
        return croissantRougeRepository.getEmployeByRegion(region, nomEntreprise);
    }

    @Override
    public Float getTotalDonation(Date date1, Date date2) {
        return donRepository.calculateTotalDonationsBetweenDates(date1, date2); // Updated method name
    }
}
