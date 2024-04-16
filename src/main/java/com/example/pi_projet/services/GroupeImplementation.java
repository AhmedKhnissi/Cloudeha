package com.example.pi_projet.services;

import com.example.pi_projet.entities.User;
import com.example.pi_projet.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.pi_projet.entities.Groupe;
import com.example.pi_projet.entities.Option;
import com.example.pi_projet.repositories.GroupeRepository;

import java.time.Year;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class GroupeImplementation implements IGroupeService{
    UserRepository userRepository;
    GroupeRepository groupeRepository;
    @Override
    public Groupe addGroupe(Groupe groupe) {
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
    public Groupe AssignUserTOGroupe(Long idUser, Long idGroupe) {
        User user = userRepository.findUserById(idUser);
        Groupe groupe = groupeRepository.findGroupesByIdGroupe(idGroupe);

        if (user != null && groupe != null) {
            user.getGroupeSet().add(groupe);
            groupe.getUserSet().add(user);
            return groupe;
        }
        else
            throw new RuntimeException("Khnissi try again mfs");
    }



}
