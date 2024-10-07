package tn.esprit.donation.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.donation.entities.Don;
import tn.esprit.donation.entities.TypeDons;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class DonRepositoryTest {

    @Autowired
    private DonRepository donRepository;

    @BeforeEach
    void setUp() {
        // Création de dons de test avec des types et dates variées
        Don don1 = new Don();
        don1.setType(TypeDons.FOOD);
        don1.setDate(new Date());
        don1.setMontant(100.0f);
        donRepository.save(don1);

        Don don2 = new Don();
        don2.setType(TypeDons.CLOTHING);
        don2.setDate(new Date());
        don2.setMontant(200.0f);
        donRepository.save(don2);

        Don don3 = new Don();
        don3.setType(TypeDons.FOOD);
        don3.setDate(new Date());
        don3.setMontant(150.0f);
        donRepository.save(don3);
    }

    @Test
    void testFindByType() {
        List<Don> dons = donRepository.findByType(TypeDons.FOOD); // Retourne directement List<Don>
        assertNotNull(dons);
        assertEquals(2, dons.size());
    }

    @Test
    void testFindDonByMonth() {
        List<Don> donsDuMois = donRepository.findDonByMonth(); // Retourne directement List<Don>
        assertNotNull(donsDuMois);
        assertEquals(3, donsDuMois.size());
    }

    @Test
void testGetTotalByDon() {
    // Test pour calculer le total des montants des dons entre deux dates
    Date dateDebut = new Date(System.currentTimeMillis() - (1000L * 60 * 60 * 24 * 30)); // il y a 30 jours
    Date dateFin = new Date();

    Float total = donRepository.calculateTotalDonationsBetweenDates(dateDebut, dateFin); // Change this line

    // Vérifie le total des dons
    assertNotNull(total);
    assertEquals(450.0f, total);
}
}
