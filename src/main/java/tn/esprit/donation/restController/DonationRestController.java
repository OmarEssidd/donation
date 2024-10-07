package tn.esprit.donation.restController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tn.esprit.donation.entities.Don;
import tn.esprit.donation.entities.Employe;
import tn.esprit.donation.entities.Entreprise;
import tn.esprit.donation.entities.TypeDons;
import tn.esprit.donation.services.IServices;

import java.util.Date;
import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@Slf4j
public class DonationRestController {

    private IServices iServices;

    @PostMapping("/addEntreprise")
    public Entreprise addEntreprise(@RequestBody Entreprise entreprise){
        return iServices.addEntreprise(entreprise);
    }

    @PostMapping("/addEmployeAndAssignToEntreprise/{nomEntreprise}")
    public Employe addEmployeAndAssignToEntreprise(@RequestBody Employe employe, @PathVariable String nomEntreprise){
        return iServices.addEmployeAndAssignToEntreprise(employe, nomEntreprise);
    }

    @PostMapping("/addDon")
    public Don addDon(@RequestBody Don don){
        return iServices.addDon(don);
    }

    @GetMapping("/getDonByType/{type}")
    public Set<Don> getDonByType(@PathVariable TypeDons type){
        return iServices.getDonByType(type);
    }

    @GetMapping("/getEmployeByRegion/{region}/{nomentreprise}")
    public List<Employe> getEmployeByRegion(@PathVariable String region, @PathVariable String nomentreprise) {
        return iServices.getEmployeByRegion(region, nomentreprise);
    }

    @GetMapping("/getTotalDonation/{date1}/{date2}")
    public Float getTotalDonation(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date1, 
                                  @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date2) {
        return iServices.getTotalDonation(date1, date2);
    }

    @GetMapping("/getEmployeByDon")
    public void getEmployeByDon() {
        iServices.getEmployeByDon();  // Calls the method from ServiceIMP
    }
}
