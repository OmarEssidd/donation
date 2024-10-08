package tn.esprit.donation.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tn.esprit.donation.entities.TypeDons;
import tn.esprit.donation.entities.Don;
import tn.esprit.donation.entities.Employe;
import tn.esprit.donation.entities.Entreprise;
import tn.esprit.donation.repositories.CroissantRougeRepository;
import tn.esprit.donation.repositories.DonRepository;
import tn.esprit.donation.repositories.EmployeRepository;
import tn.esprit.donation.repositories.EntrepriseRepository;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    @Mock
    private EntrepriseRepository entrepriseRepository;

    @Mock
    private EmployeRepository employeRepository;

    @Mock
    private DonRepository donRepository;

    @Mock
    private CroissantRougeRepository croissantRougeRepository;

    @InjectMocks
    private ServiceIMP service;

    private Employe employee1;
    private Employe employee2;

    @BeforeEach
    void setUp() {
        // Initialiser les objets Employe pour être utilisés dans les tests
        employee1 = new Employe();
        employee1.setIdEmploye(1L);
        employee2 = new Employe();
        employee2.setIdEmploye(2L);
    }

    // Test pour addEntreprise
    @Test
    public void testAddEntreprise() {
        Entreprise entreprise = new Entreprise();
        when(entrepriseRepository.save(entreprise)).thenReturn(entreprise);

        Entreprise savedEntreprise = service.addEntreprise(entreprise);

        verify(entrepriseRepository, times(1)).save(entreprise);
        assertEquals(entreprise, savedEntreprise);
    }

    // Test pour getEmployeByDon
    @Test
    public void testGetEmployeByDon() {
        // Configurer des dons associés à des employés spécifiques
        Don donation1 = new Don(100.0, new Date(), TypeDons.ESPECES);
        donation1.setEmploye(employee1);

        Don donation2 = new Don(200.0, new Date(), TypeDons.NATURE);
        donation2.setEmploye(employee1);

        Don donation3 = new Don(150.0, new Date(), TypeDons.SANG);
        donation3.setEmploye(employee2);

        List<Don> currentMonthDonations = Arrays.asList(donation1, donation2, donation3);
        when(donRepository.findDonByMonth()).thenReturn(currentMonthDonations);

        List<Employe> result = service.getEmployeByDon();

        verify(donRepository, times(1)).findDonByMonth();
        // Vérifier le nombre d'employés uniques associés aux dons
        assertEquals(2, result.size());
    }

    // Test pour getEmployeByRegion
    @Test
    public void testGetEmployeByRegion() {
        String region = "Tunis";
        String nomEntreprise = "Esprit";
        
        // Configurer une liste d'employés attendue
        List<Employe> employees = Arrays.asList(new Employe(), new Employe());
        when(croissantRougeRepository.getEmployeByRegion(region, nomEntreprise)).thenReturn(employees);

        List<Employe> result = service.getEmployeByRegion(region, nomEntreprise);

        verify(croissantRougeRepository, times(1)).getEmployeByRegion(region, nomEntreprise);
        assertEquals(employees, result);
    }

    // Test pour getTotalDonation
    @Test
    public void testGetTotalDonation() {
        Date date1 = new Date();
        Date date2 = new Date(date1.getTime() + (1000 * 60 * 60 * 24)); // Ajouter 1 jour pour date2
        float totalDonation = 150.0f;
        
        // Mocker le total attendu
        when(donRepository.getTotalByDon(date1, date2)).thenReturn(totalDonation);

        float actualTotal = service.getTotalDonation(date1, date2);

        verify(donRepository, times(1)).getTotalByDon(date1, date2);
        assertEquals(totalDonation, actualTotal);
    }
}
