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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CroissantRougeRepositoryTest {

    @Autowired
    private CroissantRougeRepository croissantRougeRepository;

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @BeforeEach
    void setUp() {
        // Création des données de test

        // Création d'une entreprise
        Entreprise entreprise = new Entreprise();
        entreprise.setNomEntreprise("TechCorp");
        entreprise = entrepriseRepository.save(entreprise);

        // Création d'un employé
        Employe employe = new Employe();
        employe.setNomEmploye("John");
        employe.setPrenomEmploye("Doe");
        employe.setEntreprise(entreprise);
        employe = employeRepository.save(employe);

        // Création d'un don associé à l'employé
        Don don = new Don();
        don.setEmploye(employe);

        // Création d'un CroissantRouge associé au don
        CroissantRouge croissantRouge = new CroissantRouge();
        croissantRouge.setRegion("North");
        croissantRouge.setDons(List.of(don));
        croissantRougeRepository.save(croissantRouge);
    }

    @Test
    void testGetEmployeByRegion() {
        // Exécute la méthode de test
        List<Employe> employes = croissantRougeRepository.getEmployeByRegion("North", "TechCorp");

        // Vérifie les résultats
        assertNotNull(employes);
        assertEquals(1, employes.size());
        assertEquals("John", employes.get(0).getNomEmploye());
    }
}
