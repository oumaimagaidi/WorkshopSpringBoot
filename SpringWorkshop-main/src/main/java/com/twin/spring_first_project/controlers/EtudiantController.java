package com.twin.spring_first_project.controlers;

import com.twin.spring_first_project.entities.Etudiant;
import com.twin.spring_first_project.services.EtudiantService;
import com.twin.spring_first_project.services.IEtudiantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class EtudiantController {
    IEtudiantService etudiantService;

    @RestController
    @AllArgsConstructor
    @RequestMapping("/etudiant")
    public class EtudiantRestController {
        IEtudiantService etudiantService;

        @GetMapping("/retrieve-all-etudiants")
        public List<Etudiant> getEtudiants() {
            List<Etudiant> listEtudiants = etudiantService.retrieveAllEtudiant();
            return listEtudiants;
        }
    }
}