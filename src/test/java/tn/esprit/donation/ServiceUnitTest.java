package tn.esprit.donation;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import tn.esprit.donation.entities.Don;
import tn.esprit.donation.entities.Employe;
import tn.esprit.donation.entities.Entreprise;
import tn.esprit.donation.entities.TypeDons;
import tn.esprit.donation.repositories.EmployeRepository;
import tn.esprit.donation.repositories.DonRepository;
import tn.esprit.donation.services.IServices;
import tn.esprit.donation.services.ServiceIMP;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class ServiceUnitTest {

    @InjectMocks
    private ServiceIMP service;

    @Mock
    private EmployeRepository employeRepository;

    @Mock
    private DonRepository donRepository; // Added to mock DonRepository

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetEmployeByRegion() {
        String region = "TestRegion";
        String nomEntreprise = "TestEntreprise";

        List<Employe> employes = new ArrayList<>();
        employes.add(new Employe()); // Add at least one employe for testing

        when(employeRepository.getEmployeByRegionAndEntreprise(region, nomEntreprise)).thenReturn(employes);

        List<Employe> result = service.getEmployeByRegion(region, nomEntreprise);
        
        assertEquals(employes.size(), result.size());
        verify(employeRepository).getEmployeByRegionAndEntreprise(region, nomEntreprise);
    }

    @Test
    public void testAddDon() {
        Don don = new Don();
        when(donRepository.save(don)).thenReturn(don); // Mock the save method

        Don result = service.addDon(don);
        
        assertNotNull(result);
        verify(donRepository).save(don); // Verify the save was called
    }

    @Test
    public void testAddEmployeAndAssignToEntreprise() {
        Employe employe = new Employe();
        String nomEntreprise = "TestEntreprise";
        Entreprise entreprise = new Entreprise();
        when(employeRepository.save(employe)).thenReturn(employe);
        when(employeRepository.findByNomEntreprise(nomEntreprise)).thenReturn(entreprise);

        Employe result = service.addEmployeAndAssignToEntreprise(employe, nomEntreprise);

        assertNotNull(result);
        verify(employeRepository).save(employe);
        verify(employeRepository).findByNomEntreprise(nomEntreprise);
    }

    // Add more test methods for other IServices methods
}
