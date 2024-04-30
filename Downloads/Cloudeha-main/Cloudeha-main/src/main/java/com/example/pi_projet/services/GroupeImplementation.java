package com.example.pi_projet.services;

import com.example.pi_projet.entities.*;
import com.example.pi_projet.repositories.RendezVousRepository;
import com.example.pi_projet.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.example.pi_projet.repositories.GroupeRepository;

import java.time.Year;
import java.util.*;

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
        User user =  userRepository.findUserById(idUser);
        groupe.setUserSet(Collections.singleton(user));
        user.setGroupeSet(Collections.singleton(groupe));
        groupe.setNom_Tuteur(user.getPrenom() + " " +user.getNom());
        groupe.setStatut("ENCORE DES PLACES");
        groupe.setEtat(1);
        //groupe.setStatut((User) user.getUserRole().getRole().toString()); //bech tekhou mtaa l user li conn
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
        return groupeRepository.SelectPerYear(Annee_Scolaire);
    }

    @Override
    public Groupe AssignUserTOGroupe(Long idUser, Long idGroupe) { //hne bech nzidodha ken l user admin
        User user = userRepository.findUserById(idUser);
        Groupe groupe = groupeRepository.findGroupesByIdGroupe(idGroupe);
        Optional<User> userOptional = groupe.getUserSet().stream().findFirst();
        User user1 = userOptional.orElse(null);


        if (user != null && groupe != null && !(groupe.getUserSet().contains(user)) && groupe.getEtat() < 7 ) {

                user.getGroupeSet().add(groupe);
                groupe.getUserSet().add(user);
            groupe.setEtat(groupe.getEtat()+1);

            userRepository.save(user);
            groupeRepository.save(groupe);
            return groupe;
        }




        else
            throw new RuntimeException("Khnissi try again mfs");
    }



   // @Scheduled(cron = "0 * * * * * ")
    @Override
    public void AssignTuteurToGroupe() {
        List<User> tuteurs = userRepository.findUsersByUserRoleRole(Role.Tuteur); // Filtrer les utilisateurs ayant le rôle de "Tuteur"
        List<String> specialites = Arrays.asList("DS", "BI", "ArcTIC", "SAE", "INFINI");
        List<Groupe> groupeList = groupeRepository.findAll();

        // Map pour stocker les tuteurs affectés à chaque spécialité
        Map<String, Queue<User>> specialitesTuteurs = new HashMap<>();
        for (String specialite : specialites) {
            specialitesTuteurs.put(specialite, new LinkedList<>());
        }

        // Affecter les tuteurs à chaque spécialité en fonction de leur ordre dans la liste
        for (int i = 0; i < tuteurs.size(); i++) {
            int index = i % specialites.size(); // Utiliser le modulo pour boucler à travers les spécialités
            String specialite = specialites.get(index);
            specialitesTuteurs.get(specialite).add(tuteurs.get(i));
        }

        for (Groupe groupe : groupeList) {
            if (groupe.getEtat() == 6) {
                List<User> tuteursAffectes = new ArrayList<>();
                String option = String.valueOf(groupe.getOption());
                if ("ArcTIC".equals(option)) {
                    tuteursAffectes.addAll(takeTuteurs(specialitesTuteurs.get("SAE"), 1));
                    tuteursAffectes.addAll(takeTuteurs(specialitesTuteurs.get("ArcTIC"), 1));
                    tuteursAffectes.addAll(takeTuteurs(specialitesTuteurs.get("TWIN"), 1));
                } else if ("INFINI".equals(option)) {
                    tuteursAffectes.addAll(takeTuteurs(specialitesTuteurs.get("INFINI"), 1));
                    tuteursAffectes.addAll(takeTuteurs(specialitesTuteurs.get("BI"), 1));
                    tuteursAffectes.addAll(takeTuteurs(specialitesTuteurs.get("TWIN"), 1));
                } else if ("BI".equals(option)) {
                    tuteursAffectes.addAll(takeTuteurs(specialitesTuteurs.get("BI"), 1));
                    tuteursAffectes.addAll(takeTuteurs(specialitesTuteurs.get("DS"), 1));
                    tuteursAffectes.addAll(takeTuteurs(specialitesTuteurs.get("TWIN"), 1));
                }
                // Assigner les tuteurs affectés au groupe
                groupe.getUserSet().add((User) tuteursAffectes);
                // Mettre à jour le groupe dans la base de données
                groupe.setStatut("COMPLET");
                groupeRepository.save(groupe);
            }
        }
    }

    // Méthode pour prendre les tuteurs de la file jusqu'à ce que le nombre spécifié soit atteint
    private List<User> takeTuteurs(Queue<User> tuteurs, int count) {
        List<User> result = new ArrayList<>();
        while (count > 0 && !tuteurs.isEmpty()) {
            result.add(tuteurs.poll());
            count--;
        }
        return result;


    }


  //  @Scheduled(cron = "0 * * * * * ")
    @Override
    public void calculateTotalScore() {
        List<RendezVous> rendezVousList = rendezVousRepository.findAll();
        List<Groupe> groupeList = groupeRepository.findAll();
        Float total =0.0F;
        Map<Long, Integer> totalScores = new HashMap<>(); // Map pr stocker les scores totaux per groupe ya jon

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
