package com.twin.spring_first_project.controlers;

import com.twin.spring_first_project.entities.Chambre;
import com.twin.spring_first_project.entities.TypeChambre;
import com.twin.spring_first_project.services.ChambreService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chambre")
@AllArgsConstructor
public class ChambreController {

    private final ChambreService chambreService;

    // Récupérer toutes les chambres
    @GetMapping("/")
    public List<Chambre> getAllChambres() {
        return chambreService.retrieveAllChambre();
    }

    // Récupérer une chambre par son ID
    @GetMapping("/{idChambre}")
    public ResponseEntity<Chambre> getChambre(@PathVariable Long idChambre) {
        return chambreService.retrieveChambre(idChambre)
                .map(chambre -> new ResponseEntity<>(chambre, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Ajouter une nouvelle chambre
    @PostMapping("/")
    public ResponseEntity<Chambre> addChambre(@RequestBody Chambre chambre) {
        Chambre savedChambre = chambreService.updateChambre(chambre);
        return new ResponseEntity<>(savedChambre, HttpStatus.CREATED);
    }

    // Mettre à jour une chambre existante
    @PutMapping("/{idChambre}")
    public ResponseEntity<Chambre> updateChambre(@PathVariable Long idChambre, @RequestBody Chambre chambre) {
        if (!chambreService.retrieveChambre(idChambre).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        chambre.setIdChambre(idChambre);
        Chambre updatedChambre = chambreService.updateChambre(chambre);
        return new ResponseEntity<>(updatedChambre, HttpStatus.OK);
    }

    // Supprimer une chambre
    @DeleteMapping("/{idChambre}")
    public ResponseEntity<Void> deleteChambre(@PathVariable Long idChambre) {
        if (!chambreService.retrieveChambre(idChambre).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        chambreService.removeChambre(idChambre);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Récupérer les chambres d'un bloc spécifique
    @GetMapping("/bloc/{nomBloc}")
    public List<Chambre> getChambresByBloc(@PathVariable String nomBloc) {
        return chambreService.getChambresByBlocName(nomBloc);
    }

    // Récupérer les chambres validées selon les réservations
    @GetMapping("/valid/{valide}")
    public List<Chambre> getChambresByReservationValidity(@PathVariable Boolean valide) {
        return chambreService.getChambresByReservationValidity(valide);
    }

    // Récupérer les chambres d'un bloc avec une capacité supérieure à une valeur donnée
    @GetMapping("/bloc/{nomBloc}/capacite/{capacite}")
    public List<Chambre> getChambresByBlocAndCapacity(@PathVariable String nomBloc, @PathVariable long capacite) {
        return chambreService.getChambresByBlocAndCapacity(nomBloc, capacite);
    }

    // Requêtes personnalisées
    @GetMapping("/custom/bloc/{nom}")
    public List<Chambre> getChambresByBlocCustom(@PathVariable String nom) {
        return chambreService.retrieveChambresByBloc(nom);
    }

    @GetMapping("/custom/bloc/{nom}/type/{typeChambre}")
    public List<Chambre> getChambresByTypeAndBlocCustom(@PathVariable String nom, @PathVariable TypeChambre typeChambre) {
        return chambreService.retrieveChambresByTypeAndBloc(nom, typeChambre);
    }

    @GetMapping("/custom/valid/{valide}")
    public List<Chambre> getChambresByValidityCustom(@PathVariable Boolean valide) {
        return chambreService.retrieveChambresByValidity(valide);
    }

    @GetMapping("/custom/bloc/{nom}/capacite/{nombre}")
    public List<Chambre> getChambresByBlocAndCapacityCustom(@PathVariable String nom, @PathVariable long nombre) {
        return chambreService.retrieveChambresByBlocAndCapacity(nom, nombre);
    }
}
