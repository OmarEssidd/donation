package tn.esprit.donation.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tn.esprit.donation.entities.Don;
import tn.esprit.donation.entities.TypeDons;

import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // This annotation will configure an in-memory database
public class DonRepositoryTest {

    @Autowired
    private DonRepository donRepository; // Autowire the actual repository

    @BeforeEach // Set up mock data before each test
    public void setUp() {
        Don don1 = new Don(100.0, new Date(), TypeDons.ESPECES);
        Don don2 = new Don(50.0, new Date(), TypeDons.NATURE);
        Don don3 = new Don(25.0, new Date(), TypeDons.SANG);
        
        // Save test data to the in-memory database
        donRepository.saveAll(List.of(don1, don2, don3));
    }

    @Test
    public void testFindByType() {
        // Test with each type of don
        for (TypeDons type : TypeDons.values()) {
            Set<Don> donsForType = donRepository.findByType(type); // Assume this returns a Set
            assertEquals(1, donsForType.size(), "Incorrect number of dons for type: " + type);
            // Verify that the found don corresponds to the tested type
            assertTrue(donsForType.stream().allMatch(don -> don.getType() == type));
        }
    }

    // Add more tests for other DonRepository methods using similar approach
}
