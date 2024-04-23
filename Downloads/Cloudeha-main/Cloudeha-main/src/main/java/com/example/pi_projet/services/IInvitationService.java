package com.example.pi_projet.services;


import com.example.pi_projet.entities.Groupe;
import com.example.pi_projet.entities.Invitation;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IInvitationService {
    Invitation addInvitation(Long idUser, Long idGroupe);

    void deleteInvitation(Long idInvitation);

    List<Invitation> retrieveInvitations (Long idGroupe);
}