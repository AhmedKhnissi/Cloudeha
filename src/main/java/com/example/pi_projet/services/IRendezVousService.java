package com.example.pi_projet.services;

import com.example.pi_projet.entities.RendezVous;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public interface IRendezVousService {
    RendezVous addRDV(RendezVous rendezVous);
    RendezVous updateRDV(RendezVous rendezVous);
    void deleteRDV(Long idRdv);

    RendezVous recupererRdv(Long idRdv);

    List<RendezVous> recupererAllRdv ();

    List<RendezVous> listeRdvByDate (LocalDate date);

    List<RendezVous> ListRdvBetweenDates (LocalDate dateDeb , LocalDate dateFin);

    List<RendezVous> LISTRdvByGroupe (Long idGroupe);

    List<String> heuresDisponibles(LocalDate date);

    RendezVous AssignRdvToGroupe(Long idRdv , Long idGroupe);
}
