package com.example.pi_projet.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import com.example.pi_projet.entities.RendezVous;
import com.example.pi_projet.repositories.RendezVousRepository;

@Service
@AllArgsConstructor
public class RendezVousImplementation implements IRendezVousService {
    RendezVousRepository rendezVousRepository;
    @Override
    public RendezVous addRDV(RendezVous rendezVous) {
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public RendezVous updateRDV(RendezVous rendezVous) {
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public void deleteRDV(Long idRdv) {
        rendezVousRepository.deleteById(idRdv);
    }

    @Override
    public RendezVous recupererRdv(Long idRdv) {
        return rendezVousRepository.findById(idRdv).get();
    }

    @Override
    public List<RendezVous> recupererAllRdv() {
        return rendezVousRepository.findAll();
    }

    @Override
    public List<RendezVous> listeRdvByDate(LocalDate date) {
        return rendezVousRepository.findRendezVousByDate(date);
    }

    @Override
    public List<RendezVous> ListRdvBetweenDates(LocalDate dateDeb, LocalDate dateFin) {
        return rendezVousRepository.findRendezVousByDateBetween(dateDeb,dateFin);
    }

    @Override
    public List<RendezVous> LISTRdvByGroupe(Long idGroupe) {
        return rendezVousRepository.findRendezVousByGroupe_IdGroupe(idGroupe);
    }

    @Override
    public List<String> heuresDisponibles(LocalDate date) {
        List<String> heuresOccupées = rendezVousRepository.findHeureByDate(date);
        List<String> heuresDisponibles = new ArrayList<>();


        LocalTime heureDebut = LocalTime.of(8, 0);
        LocalTime heureFin = LocalTime.of(17, 0);
        while (heureDebut.isBefore(heureFin)) {
            String heure = heureDebut.toString();
            if (!heuresOccupées.contains(heure)) {
                heuresDisponibles.add(heure);
            }
            heureDebut = heureDebut.plusHours(1);
        }

        return heuresDisponibles;
    }
}
