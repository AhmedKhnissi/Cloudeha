package com.example.pi_projet.services;

import com.example.pi_projet.entities.Groupe;
import com.example.pi_projet.entities.Option;
import com.example.pi_projet.entities.RendezVous;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Year;
import java.util.List;

public interface IGroupeService {
    Groupe addGroupe (Groupe groupe, Long idUser);
    Groupe updateGroupe (Groupe groupe);
    void deleteGroupe (Long idGroupe);

    Groupe recupererGroupe (Long idGroupe);

    List<Groupe> recupererAllGroupes ();

    List<Groupe> listeGroupesByOptions (Option option);

    List<Groupe> ListGroupeByAnneScolaireI (Year Annee_Scolaire);

    Groupe AssignUserTOGroupe(Long idUser, Long idGroupe);




    @Scheduled(cron = "0 * * * * * ")
    void calculateTotalScore();
}
