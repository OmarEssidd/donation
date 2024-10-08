package tn.esprit.donation.restController;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import tn.esprit.donation.entities.Don;
import tn.esprit.donation.entities.TypeDons;
import tn.esprit.donation.repositories.DonRepository;
import tn.esprit.donation.services.ServiceIMP;

@ExtendWith(MockitoExtension.class)
public class DonationRestControllerTest {

    @InjectMocks
    private DonationRestController donationRestController;

    @Mock
    private ServiceIMP donationService;

    @Mock
    private DonRepository donRepository;

    private MockMvc mockMvc;

    private Don existingDon;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(donationRestController).build();
        
        // Créer un don existant pour les tests
        existingDon = new Don(100.0, new Date(), TypeDons.NATURE);
        existingDon.setIdDon(1L);
    }

    @Test
    public void testCreateDon() {
        Don newDon = new Don(100.0, new Date(), TypeDons.ESPECES);
        when(donationService.createDon(any(Don.class))).thenReturn(newDon);

        ResponseEntity<Don> responseEntity = donationRestController.createDon(newDon);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(newDon.getMontant(), responseEntity.getBody().getMontant());
        assertEquals(newDon.getType(), responseEntity.getBody().getType());
    }

    @Test
    void testGetDonById() throws Exception {
        // Création d'un objet Don fictif
        Don mockDon = new Don();
        mockDon.setIdDon(1L); // Assurez-vous que l'ID est correct
        // Configuration du comportement du mock
        when(donRepository.findById(1L)).thenReturn(Optional.of(mockDon));
    
        // Appel à l'API et vérification du statut
        mockMvc.perform(get("/donations/1")) // Assurez-vous que ce chemin correspond à votre API
            .andExpect(status().isOk()) // Attendez un statut 200 OK
            .andExpect(jsonPath("$.idDon").value(1L)); // Vérifiez que l'ID est correct dans la réponse
    }

    @Test
    void testGetDonByIdNotFound() throws Exception {
        // Configuration du mock pour renvoyer un Optional vide
        when(donRepository.findById(2L)).thenReturn(Optional.empty()); // Assurez-vous que l'ID ne correspond à aucun objet existant
    
        // Appel à l'API et vérification du statut
        mockMvc.perform(get("/donations/2")) // Utilisez un ID qui n'existe pas
            .andExpect(status().isNotFound()); // Attendez un statut 404 NOT FOUND
    }
}
