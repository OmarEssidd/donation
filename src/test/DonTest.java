package tn.esprit.donation.entities;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;   
import tn.esprit.donation.entities.TypeDons;


public class DonTest {

    @Test
    public void testDonCreation() {
        Don don = new Don();

        don.setIdDon(1L);
        don.setMontant(100.50);
        don.setDate(new Date());
        don.setType(TypeDons.FINANCIER);
        don.setEmploye(employe); // Set the Employe if required

        assertNotNull(don);
        assertEquals(1L, don.getIdDon());
        assertEquals(100.50, don.getMontant());
        assertNotNull(don.getDate());
        assertEquals(TypeDons.FINANCIER, don.getType());
        // Assert Employe if relevant (optional)
    }
}