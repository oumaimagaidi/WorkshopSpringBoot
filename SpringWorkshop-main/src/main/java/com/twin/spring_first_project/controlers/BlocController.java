package com.twin.spring_first_project.controlers;

import com.twin.spring_first_project.entities.Bloc;
import com.twin.spring_first_project.services.BlocService;
import com.twin.spring_first_project.services.IBlocService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/bloc")
@AllArgsConstructor
public class BlocController {

    private final BlocService blocService;

    // GET all Blocs
    @GetMapping
    public ResponseEntity<List<Bloc>> getAllBlocs() {
        List<Bloc> blocs = blocService.retrieveAllBloc();
        return new ResponseEntity<>(blocs, HttpStatus.OK);
    }

    // GET a Bloc by ID
    @GetMapping("/{id}")
    public ResponseEntity<Bloc> getBlocById(@PathVariable Long id) {
        Optional<Bloc> bloc = blocService.retrieveBloc(id);
        return bloc.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // POST a new Bloc
    @PostMapping
    public ResponseEntity<Bloc> createBloc(@RequestBody Bloc bloc) {
        Bloc createdBloc = blocService.updateBloc(bloc);
        return new ResponseEntity<>(createdBloc, HttpStatus.CREATED);
    }

    // PUT (update) an existing Bloc
    @PutMapping("/{id}")
    public ResponseEntity<Bloc> updateBloc(@PathVariable Long id, @RequestBody Bloc bloc) {
        if (!blocService.retrieveBloc(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bloc.setIdBloc(id); // Set the ID to ensure the existing Bloc is updated
        Bloc updatedBloc = blocService.updateBloc(bloc);
        return new ResponseEntity<>(updatedBloc, HttpStatus.OK);
    }

    // DELETE a Bloc by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBloc(@PathVariable Long id) {
        if (!blocService.retrieveBloc(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        blocService.removeBloc(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/searchByNom")
    public ResponseEntity<Bloc> getBlocByNom(@RequestParam String nomBloc) {
        Bloc bloc = blocService.findByNomBloc(nomBloc);
        return bloc != null ? new ResponseEntity<>(bloc, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

