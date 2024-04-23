package com.example.pi_projet.services;

import com.example.pi_projet.entities.RendezVous;
import com.example.pi_projet.entities.User;
import com.example.pi_projet.repositories.RendezVousRepository;
import com.example.pi_projet.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.example.pi_projet.entities.Groupe;
import com.example.pi_projet.entities.Option;
import com.example.pi_projet.repositories.GroupeRepository;

import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class GroupeImplementation implements IGroupeService{
    UserRepository userRepository;
    GroupeRepository groupeRepository;
    RendezVousRepository rendezVousRepository;
    @Override
    public Groupe addGroupe(Groupe groupe, Long idUser) {
        List<Groupe> groupeList = groupeRepository.findAll();
        User user = userRepository.findUserById(idUser);
        groupe.getUserSet().add(user);
        groupe.setStatut(user.getUserRole().getRole().toString()); //bech tekhou mtaa l user li conn
        return groupeRepository.save(groupe);
    }

    @Override
    public Groupe updateGroupe(Groupe groupe) {
        return groupeRepository.save(groupe);
    }

    @Override
    public void deleteGroupe(Long idGroupe) {
        groupeRepository.deleteById(idGroupe);
    }

    @Override
    public Groupe recupererGroupe(Long idGroupe) {
        return groupeRepository.findById(idGroupe).get();
    }

    @Override
    public List<Groupe> recupererAllGroupes() {
        return groupeRepository.findAll();
    }

    @Override
    public List<Groupe> listeGroupesByOptions(Option option) {
        return groupeRepository.findGroupeByOption(option);
    }

    @Override
    public List<Groupe> ListGroupeByAnneScolaireI(Year Annee_Scolaire) {
        return groupeRepository.findGroupeByAnnee(Annee_Scolaire);
    }

    @Override
    public Groupe AssignUserTOGroupe(Long idUser, Long idGroupe) { //hne bech nzidodha ken l user admin
        User user = userRepository.findUserById(idUser);
        Groupe groupe = groupeRepository.findGroupesByIdGroupe(idGroupe);
        User user1 = (User) groupe.getUserSet().stream().limit(1);

        if (user != null && groupe != null && !(groupe.getUserSet().contains(user)) ) {
            if( /*user == user1 ||*/ user1.getUserRole().equals("Tuteur")) // l user li conn
            {
                user.getGroupeSet().add(groupe);
                groupe.getUserSet().add(user);
                    if (user.getUserRole().getRole().name().equals("Tuteur"))
                    {groupe.setNom_Tuteur(user.getUserRole().getRole().name());}
            }
            else System.out.println("You're not Allowed to add new user to the groupe mfs");
            userRepository.save(user);
            groupeRepository.save(groupe);
            return groupe;
        }
        else
            throw new RuntimeException("Khnissi try again mfs");
    }


   // @Scheduled(cron = "0 * * * * * ")
    @Override
    public void calculateTotalScore() {
        List<RendezVous> rendezVousList = rendezVousRepository.findAll();
        List<Groupe> groupeList = groupeRepository.findAll();
        int total =0;
        Map<Long, Integer> totalScores = new HashMap<>(); // Map pour stocker les scores totaux par groupe

        for (RendezVous rendezVous : rendezVousList) {
            Long groupId = rendezVous.getGroupe().getIdGroupe();
            int points = (int) rendezVous.getPoints();

            totalScores.put(groupId, totalScores.getOrDefault(groupId, 0) + points);
        }

        for (Groupe groupe : groupeList) {
            Long groupId = groupe.getIdGroupe();
            int totalScore = totalScores.getOrDefault(groupId, 0);
            groupe.setTotal_Score(totalScore);
            groupeRepository.save(groupe);
            System.out.println("Score total pour le groupe " + groupId + " : " + totalScore);
        }



        log.info(String.valueOf(totalScores));
    }


}
