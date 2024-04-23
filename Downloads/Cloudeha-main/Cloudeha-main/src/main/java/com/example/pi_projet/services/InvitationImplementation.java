package com.example.pi_projet.services;


import com.example.pi_projet.entities.Groupe;
import com.example.pi_projet.entities.Invitation;
import com.example.pi_projet.entities.User;
import com.example.pi_projet.repositories.GroupeRepository;
import com.example.pi_projet.repositories.InvitationRepository;
import com.example.pi_projet.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InvitationImplementation implements IInvitationService{
    UserRepository userRepository;
    GroupeRepository groupeRepository;
    InvitationRepository invitationRepository;
    @Override
    public Invitation addInvitation(Long idUser, Long idGroupe) {
        Invitation invitation = new Invitation();
        User user = userRepository.findUserById(idUser);
        Groupe groupe = groupeRepository.findGroupesByIdGroupe(idGroupe);
        List<Invitation> invitationList = invitationRepository.findAll();
        invitation.setUserInvitation(user);
        invitation.setGroupeInvitation(groupe);
        if (!(invitationList.contains(invitation)))
          invitationRepository.save(invitation);
        return invitation;

    }

    @Override
    public void deleteInvitation(Long idInvitation) {
        invitationRepository.deleteById(idInvitation);
    }

    @Override
    public List<Invitation> retrieveInvitations(Long idGroupe) {
        return invitationRepository.findInvitationsByGroupeInvitation_IdGroupe(idGroupe);
    }


}
