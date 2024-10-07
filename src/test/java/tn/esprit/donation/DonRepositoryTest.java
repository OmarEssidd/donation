package tn.esprit.donation.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.donation.entities.Don;
import tn.esprit.donation.entities.TypeDons;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class DonRepositoryTest {

    @Autowired
    private DonRepository donRepository;

    @BeforeEach
    void setUp() {
        // Creating test donations with varied types and dates
        Don don1 = new Don();
        don1.setType(TypeDons.FOOD);
        don1.setDate(new Date());
        don1.setMontant(100.0f);
        donRepository.save(don1);

        Don don2 = new Don();
        don2.setType(TypeDons.CLOTHING);
        don2.setDate(new Date());
        don2.setMontant(150.0f);
        donRepository.save(don2);
    }

    @Test
    void testFindAll() {
        List<Don> dons = donRepository.findAll();
        assertNotNull(dons);
        assertEquals(2, dons.size());
    }

    @Test
    void testFindByType() {
        List<Don> foodDons = donRepository.findByType(TypeDons.FOOD);
        assertNotNull(foodDons);
        assertEquals(1, foodDons.size());
        assertEquals(TypeDons.FOOD, foodDons.get(0).getType());
    }

    // Add more test methods as needed
}
