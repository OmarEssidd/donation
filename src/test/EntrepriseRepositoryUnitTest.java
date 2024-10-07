package tn.esprit.donation.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tn.esprit.donation.entities.Entreprise;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
class EntrepriseRepositoryUnitTest {

    @Mock
    EntrepriseRepository entrepriseRepository;

    @BeforeEach
    void setUp() {
        entrepriseRepository.save(new Entreprise(1L, "TechCorp"));
        entrepriseRepository.save(new Entreprise(2L, "Innovate"));
    }

    @AfterEach
    void destroy() {
        entrepriseRepository.deleteAll();
    }

    @Test
    void testFindByNomEntreprise() {
        Entreprise savedEntreprise = new Entreprise(3L, "NextGen");
        entrepriseRepository.save(savedEntreprise);

        Entreprise foundEntreprise = entrepriseRepository.findByNomEntreprise("NextGen");

        assertThat(foundEntreprise).isNotNull();
        assertThat(foundEntreprise.getNomEntreprise()).isEqualTo("NextGen");
    }

    @Test
    void testGetInvalidEntreprise() {
        assertThrows(NoSuchElementException.class, () -> {
            entrepriseRepository.findById(999L).get();
        });
    }

    @Test
    void testDeleteEntreprise() {
        Entreprise savedEntreprise = new Entreprise(4L, "DeleteMe");
        entrepriseRepository.save(savedEntreprise);
        entrepriseRepository.delete(savedEntreprise);

        assertThrows(NoSuchElementException.class, () -> {
            entrepriseRepository.findById(4L).get();
        });
    }
}
