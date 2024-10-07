package tn.esprit.donation.restController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.donation.entities.Don;
import tn.esprit.donation.entities.Employe;
import tn.esprit.donation.entities.Entreprise;
import tn.esprit.donation.entities.TypeDons;
import tn.esprit.donation.services.IServices;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api") 
public class DonationRestController {

    private final IServices iServices;

    @PostMapping("/addEntreprise")
    public ResponseEntity<Entreprise> addEntreprise(@RequestBody Entreprise entreprise) {
        Entreprise addedEntreprise = iServices.addEntreprise(entreprise);
        return new ResponseEntity<>(addedEntreprise, HttpStatus.CREATED);
    }

    @PostMapping("/addEmployeAndAssignToEntreprise/{nomEntreprise}")
    public ResponseEntity<Employe> addEmployeAndAssignToEntreprise(@RequestBody Employe employe, 
                                                                    @PathVariable String nomEntreprise) {
        Employe addedEmploye = iServices.addEmployeAndAssignToEntreprise(employe, nomEntreprise);
        return new ResponseEntity<>(addedEmploye, HttpStatus.CREATED);
    }

    @PostMapping("/addDon")
    public ResponseEntity<Don> addDon(@RequestBody Don don) {
        Don addedDon = iServices.addDon(don);
        return new ResponseEntity<>(addedDon, HttpStatus.CREATED);
    }

    @GetMapping("/getDonByType/{type}")
    public ResponseEntity<List<Don>> getDonByType(@PathVariable TypeDons type) {
        List<Don> donations = iServices.getDonByType(type);
        return new ResponseEntity<>(donations, HttpStatus.OK);
    }

    @GetMapping("/getEmployeByRegion/{region}/{nomEntreprise}")
    public ResponseEntity<List<Employe>> getEmployeByRegion(@PathVariable String region, 
                                                             @PathVariable String nomEntreprise) {
        List<Employe> employes = iServices.getEmployeByRegion(region, nomEntreprise);
        return new ResponseEntity<>(employes, HttpStatus.OK);
    }

    @GetMapping("/getTotalDonation/{date1}/{date2}")
    public ResponseEntity<Float> getTotalDonation(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date1, 
                                                  @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date2) {
        Float totalDonation = iServices.getTotalDonation(date1, date2);
        return new ResponseEntity<>(totalDonation, HttpStatus.OK);
    }

    @GetMapping("/getEmployeByDon")
    public ResponseEntity<Void> getEmployeByDon() {
        iServices.getEmployeByDon();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
