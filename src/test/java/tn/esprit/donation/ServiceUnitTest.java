package tn.esprit.donation;

import static org.mockito.Mockito.*;

import tn.esprit.donation.entities.Don;
import tn.esprit.donation.entities.Employe;
import tn.esprit.donation.entities.Entreprise;
import tn.esprit.donation.entities.TypeDons;
import tn.esprit.donation.repositories.EmployeRepository;
import tn.esprit.donation.services.IServices;
import tn.esprit.donation.services.ServiceIMP;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceUnitTest {

    @InjectMocks
    private ServiceIMP service;

    @Mock
    private EmployeRepository employeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetEmployeByRegion() {
        String region = "TestRegion";
        String nomEntreprise = "TestEntreprise";

        List<Employe> employes = new ArrayList<>();
        // Ajoutez des employes pour les tests
        employes.add(new Employe());

        when(employeRepository.getEmployeByRegionAndEntreprise(region, nomEntreprise)).thenReturn(employes);

        List<Employe> result = service.getEmployeByRegion(region, nomEntreprise);
        
        // Assertions pour vérifier le résultat
        assertEquals(employes.size(), result.size());
        verify(employeRepository).getEmployeByRegionAndEntreprise(region, nomEntreprise);
    }

    @Test
    public void testAddDon() {
        Don don = new Don();
        // Ajoutez des assertions pour vérifier l'ajout du Don
        Don result = service.addDon(don);
        assertNotNull(result);
    }

    // Ajoutez d'autres tests pour les autres méthodes de IServices
}
