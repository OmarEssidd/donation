package tn.esprit.donation.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.donation.entities.CroissantRouge;
import tn.esprit.donation.entities.Don;
import tn.esprit.donation.entities.Employe;
import tn.esprit.donation.entities.Entreprise;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class EmployeRepositoryTest {

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private CroissantRougeRepository croissantRougeRepository;

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @Autowired
    private DonRepository donRepository;

    @BeforeEach
    void setUp() {
        // Créer une entreprise
        Entreprise entreprise = new Entreprise();
        entreprise.setNomEntreprise("EntrepriseTest");
        entrepriseRepository.save(entreprise);

        // Créer un employé et l'associer à l'entreprise
        Employe employe = new Employe();
        employe.setNomEmploye("EmployeTest");
        employe.setEntreprise(entreprise);
        employeRepository.save(employe);

        // Créer un don et l'associer à l'employé
        Don don = new Don();
        don.setEmploye(employe);
        donRepository.save(don);

        // Créer un CroissantRouge et ajouter le don
        CroissantRouge croissantRouge = new CroissantRouge();
        croissantRouge.setRegion("RegionTest");
        Set<Don> dons = new HashSet<>();
        dons.add(don);
        croissantRouge.setDons(dons);
        croissantRougeRepository.save(croissantRouge);
    }

    @Test
    void testGetEmployeByRegionAndEntreprise() {
        // Exécuter la méthode à tester
        List<Employe> employes = employeRepository.getEmployeByRegionAndEntreprise("RegionTest", "EntrepriseTest");

        // Vérifier que le résultat est correct
        assertNotNull(employes);
        assertEquals(1, employes.size());
        assertEquals("EmployeTest", employes.get(0).getNomEmploye());
    }
}
