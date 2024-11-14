package com.twin.spring_first_project.repository;

import com.twin.spring_first_project.entities.Chambre;
import com.twin.spring_first_project.entities.TypeChambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre, Long> {

    // Récupérer les chambres d'un bloc spécifique
    List<Chambre> findByBlocNomBloc(String nomBloc);

    // Récupérer les chambres d'un type spécifique dans un bloc
    List<Chambre> findByTypeCAndBlocNomBloc(String type, String nomBloc);

    // Récupérer les chambres validées selon les réservations
    List<Chambre> findByReservations_EstValid(Boolean estValid);


    // Récupérer les chambres d'un bloc avec une capacité de bloc supérieure à une valeur donnée
    List<Chambre> findByBlocNomBlocAndBlocCapaciteBloc(String nomBloc, long capacite);

    // Récupérer les chambres d'un bloc via une requête personnalisée
    @Query("select c from Chambre c where c.bloc.nomBloc=:nom")
    List<Chambre> retrieveChambreByBloc(@Param("nom") String nom);

    // Récupérer les chambres d'un bloc avec un type spécifique via une requête personnalisée
    @Query("select c from Chambre c where c.typeC=:type and c.bloc.nomBloc=:nom")
    List<Chambre> retrieveChambreByTypeAndBloc(@Param("nom") String nom, @Param("type") TypeChambre type);


    // Récupérer les chambres validées via une requête personnalisée
    @Query("SELECT c FROM Chambre c JOIN c.reservations r WHERE r.estValid = :valide")
    List<Chambre> retrieveChambreByValidity(@Param("valide") Boolean valide);

    // Récupérer les chambres d'un bloc avec une capacité supérieure à une valeur donnée via une requête personnalisée
    @Query("select c from Chambre c where c.bloc.nomBloc=:nom and c.bloc.capaciteBloc>:nombre")
    List<Chambre> retrieveChambreByNomBlocAndCapacity(@Param("nom") String nom, @Param("nombre") long nombre);
}
