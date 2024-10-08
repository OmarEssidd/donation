package tn.esprit.donation.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeTest {

    @Test
    public void testGetNomEmploye() {
        Employe employe = new Employe();
        employe.setNomEmploye("John");
        assertEquals("John", employe.getNomEmploye(), "Le nom de l'employé devrait être John");
    }

    @Test
    public void testSetNomEmploye() {
        Employe employe = new Employe();
        employe.setNomEmploye("Doe");
        assertEquals("Doe", employe.getNomEmploye(), "Le nom de l'employé devrait être Doe");
    }

    @Test
    public void testGetPrenomEmploye() {
        Employe employe = new Employe();
        employe.setPrenomEmploye("Jane");
        assertEquals("Jane", employe.getPrenomEmploye(), "Le prénom de l'employé devrait être Jane");
    }

    @Test
    public void testSetPrenomEmploye() {
        Employe employe = new Employe();
        employe.setPrenomEmploye("Smith");
        assertEquals("Smith", employe.getPrenomEmploye(), "Le prénom de l'employé devrait être Smith");
    }

    @Test
    public void testGetPoste() {
        Employe employe = new Employe();
        employe.setPoste("Manager");
        assertEquals("Manager", employe.getPoste(), "Le poste de l'employé devrait être Manager");
    }

    @Test
    public void testSetPoste() {
        Employe employe = new Employe();
        employe.setPoste("Developer");
        assertEquals("Developer", employe.getPoste(), "Le poste de l'employé devrait être Developer");
    }

    @Test
    public void testGetIdEmploye() {
        Employe employe = new Employe();
        employe.setIdEmploye(1);
        assertEquals(1, employe.getIdEmploye(), "L'ID de l'employé devrait être 1");
    }

    @Test
    public void testSetIdEmploye() {
        Employe employe = new Employe();
        employe.setIdEmploye(2);
        assertEquals(2, employe.getIdEmploye(), "L'ID de l'employé devrait être 2");
    }

    // Ajoutez d'autres tests pour d'autres attributs ou méthodes si nécessaire
}
