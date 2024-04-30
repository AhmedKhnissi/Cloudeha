package com.example.pi_projet.repositories;


import com.example.pi_projet.entities.Invitation;
import com.example.pi_projet.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation,Long> {

    Invitation findInvitationByIdInvitation(Long idInvitation);
    List<Invitation> findInvitationsByGroupeInvitation_IdGroupe(Long idGroupe);

}
