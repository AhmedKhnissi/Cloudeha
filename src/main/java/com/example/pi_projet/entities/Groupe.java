package com.example.pi_projet.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Year;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Groupe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idGroupe")
    private Long idGroupe;
    private String Nom_Groupe;
    private String Nom_Tuteur;
    private Long Total_Score;
   // @Enumerated(EnumType.STRING)
    private Year annee;

    @Enumerated(EnumType.STRING)
    private Option option;


    @ManyToMany(mappedBy = "groupeSet")
    private Set<User> userSet;


}
