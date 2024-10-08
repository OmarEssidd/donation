package tn.esprit.donation.entities;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DonTest {

    @Test
    public void testSetAndGetMontant() {
        Don don = new Don();
        don.setMontant(100.0);
        assertEquals(100.0, don.getMontant(), "Le montant doit être 100.0");
    }

    @Test
    public void testSetAndGetDate() {
        Don don = new Don();
        Date date = new Date();
        don.setDate(date);
        assertNotNull(don.getDate(), "La date ne doit pas être nulle");
        assertEquals(date, don.getDate(), "La date doit correspondre à celle définie");
    }
}
