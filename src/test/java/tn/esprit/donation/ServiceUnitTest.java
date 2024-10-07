package tn.esprit.donation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.donation.entities.Don;
import tn.esprit.donation.entities.Employe;
import tn.esprit.donation.entities.Entreprise;
import tn.esprit.donation.entities.TypeDons;
import tn.esprit.donation.repositories.EntrepriseRepository;
import tn.esprit.donation.repositories.DonRepository;
import tn.esprit.donation.repositories.EmployeRepository;
import tn.esprit.donation.services.ServiceIMP;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceIMPUnitTest {

    @Mock
    private EntrepriseRepository entrepriseRepository;

    @Mock
    private EmployeRepository employeRepository;

    @Mock
    private DonRepository donRepository;

    @InjectMocks
    private ServiceIMP services;

    @BeforeEach
    void setUp() {
        // Set up initial conditions if needed
    }

    @Test
    void testAddEntreprise() {
        Entreprise entreprise = new Entreprise();
        entreprise.setNomEntreprise("TechCorp");

        when(entrepriseRepository.save(entreprise)).thenReturn(entreprise);
        Entreprise result = services.addEntreprise(entreprise);

        verify(entrepriseRepository, times(1)).save(entreprise);
        assertEquals("TechCorp", result.getNomEntreprise());
    }

    @Test
    void testAddEmployeAndAssignToEntreprise() {
        Entreprise entreprise = new Entreprise();
        entreprise.setNomEntreprise("TechCorp");

        Employe employe = new Employe();
        employe.setNomEmploye("John Doe");

        when(entrepriseRepository.findByNomEntreprise("TechCorp")).thenReturn(entreprise);
        when(employeRepository.save(employe)).thenReturn(employe);

        Employe result = services.addEmployeAndAssignToEntreprise(employe, "TechCorp");

        verify(employeRepository, times(1)).save(employe);
        assertEquals("John Doe", result.getNomEmploye());
    }

    @Test
    void testAddDon() {
        Don don = new Don();
        don.setType(TypeDons.CASH);

        when(donRepository.save(don)).thenReturn(don);
        Don result = services.addDon(don);

        verify(donRepository, times(1)).save(don);
        assertEquals(TypeDons.CASH, result.getType());
    }

    @Test
    void testGetDonByType() {
        Don don1 = new Don();
        don1.setType(TypeDons.CASH);

        Set<Don> dons = new HashSet<>();
        dons.add(don1);

        when(donRepository.findByType(TypeDons.CASH)).thenReturn(dons);
        Set<Don> result = services.getDonByType(TypeDons.CASH);

        verify(donRepository, times(1)).findByType(TypeDons.CASH);
        assertEquals(1, result.size());
    }

    @Test
    void testGetEmployeByRegion() {
        Employe employe = new Employe();
        employe.setNomEmploye("Alice");

        List<Employe> employes = Collections.singletonList(employe);

        when(employeRepository.getEmployeByRegion("North", "TechCorp")).thenReturn(employes);
        List<Employe> result = services.getEmployeByRegion("North", "TechCorp");

        verify(employeRepository, times(1)).getEmployeByRegion("North", "TechCorp");
        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0).getNomEmploye());
    }

    @Test
    void testGetTotalDonation() {
        Date date1 = new Date();
        Date date2 = new Date();

        when(donRepository.calculateTotalDonationsBetweenDates(date1, date2)).thenReturn(1000.0f);
        Float result = services.getTotalDonation(date1, date2);

        verify(donRepository, times(1)).calculateTotalDonationsBetweenDates(date1, date2);
        assertEquals(1000.0f, result);
    }
}
