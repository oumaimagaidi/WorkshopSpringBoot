package com.twin.spring_first_project.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.twin.spring_first_project.entities.Foyer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table( name = "Bloc")
public class Bloc implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idBloc")
    private long idBloc;
    @Column(name="nomBloc")
    private String nomBloc;
    @Column(name="capaciteBloc")
    private long capaciteBloc;

@ManyToOne
private Foyer foyer;

    @OneToMany (mappedBy = "bloc")
    @JsonManagedReference
    private Set<Chambre> chambres;

}