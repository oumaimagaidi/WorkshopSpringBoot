package com.twin.spring_first_project.services;

import com.twin.spring_first_project.entities.Chambre;
import com.twin.spring_first_project.entities.TypeChambre;
import com.twin.spring_first_project.repository.ChambreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ChambreService implements IChambreService {

    private final ChambreRepository chambreRepository;

    @Override
    public List<Chambre> retrieveAllChambre() {
        return chambreRepository.findAll();
    }

    @Override
    public Chambre updateChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    @Override
    public Optional<Chambre> retrieveChambre(Long idChambre) {
        return chambreRepository.findById(idChambre);
    }

    @Override
    public void removeChambre(Long idChambre) {
        chambreRepository.deleteById(idChambre);
    }

    // Method to retrieve chambres by bloc name
    public List<Chambre> getChambresByBlocName(String nomBloc) {
        return chambreRepository.findByBlocNomBloc(nomBloc);
    }

    // Method to retrieve chambres by type and bloc name
    public List<Chambre> getChambresByTypeAndBloc(String type, String nomBloc) {
        return chambreRepository.findByTypeCAndBlocNomBloc(type, nomBloc);
    }

    // Method to retrieve chambres by reservation validity
    public List<Chambre> getChambresByReservationValidity(Boolean valid) {
        return chambreRepository.findByReservations_EstValid(valid);
    }

    // Method to retrieve chambres by bloc and bloc capacity
    public List<Chambre> getChambresByBlocAndCapacity(String nomBloc, long capacite) {
        return chambreRepository.findByBlocNomBlocAndBlocCapaciteBloc(nomBloc, capacite);
    }

    // Custom query methods
    public List<Chambre> retrieveChambresByBloc(String nom) {
        return chambreRepository.retrieveChambreByBloc(nom);
    }


    public List<Chambre> retrieveChambresByTypeAndBloc(String nom, TypeChambre typeChambre) {
        return chambreRepository.retrieveChambreByTypeAndBloc(nom, typeChambre);
    }

    public List<Chambre> retrieveChambresByValidity(Boolean valide) {
        return chambreRepository.retrieveChambreByValidity(valide);
    }

    public List<Chambre> retrieveChambresByBlocAndCapacity(String nom, long nombre) {
        return chambreRepository.retrieveChambreByNomBlocAndCapacity(nom, nombre);
    }
}
