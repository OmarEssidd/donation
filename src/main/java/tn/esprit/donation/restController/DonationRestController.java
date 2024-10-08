package tn.esprit.donation.restController;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;  // Add this import
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.donation.entities.Don;
import tn.esprit.donation.services.IServices;
import tn.esprit.donation.repositories.DonRepository; // Assurez-vous que cet import est présent
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Optional;

@RestController
@RequestMapping("/donations")
@AllArgsConstructor
public class DonationRestController {
    @Autowired
    private DonRepository donRepository;

    private final IServices donationService;

    @PostMapping
    public ResponseEntity<Don> createDon(@RequestBody Don don) {
        Don createdDon = donationService.createDon(don);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDon);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Don> getDonById(@PathVariable Long id) {
        Optional<Don> don = donRepository.findById(id);
        if (don.isPresent()) {
            return ResponseEntity.ok(don.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
