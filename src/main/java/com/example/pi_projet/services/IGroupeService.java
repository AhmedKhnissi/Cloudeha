package com.example.pi_projet.services;

import com.example.pi_projet.entities.Groupe;
import com.example.pi_projet.entities.Option;

import java.time.Year;
import java.util.List;

public interface IGroupeService {
    Groupe addGroupe (Groupe groupe);
    Groupe updateGroupe (Groupe groupe);
    void deleteGroupe (Long idGroupe);

    Groupe recupererGroupe (Long idGroupe);

    List<Groupe> recupererAllGroupes ();

    List<Groupe> listeGroupesByOptions (Option option);

    List<Groupe> ListGroupeByAnneScolaireI (Year Annee_Scolaire);

    Groupe AssignUserTOGroupe(Long idUser, Long idGroupe);
}
