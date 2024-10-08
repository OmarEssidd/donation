package tn.esprit.donation.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

public class DonTest {

    private Don don;

    @BeforeEach
    public void setUp() {
        don = new Don();
        don.setMontant(100.0);
        don.setDate(new Date());
        don.setType(TypeDons.Especes);
    }

    @Test
    public void testMontantSetterGetter() {
        don.setMontant(200.0);
        assertEquals(200.0, don.getMontant());
    }

    @Test
    public void testTypeSetterGetter() {
        don.setType(TypeDons.Nature);
        assertEquals(TypeDons.Nature, don.getType());
    }
}
