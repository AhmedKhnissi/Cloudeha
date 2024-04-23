package com.example.pi_projet.controllers;

import com.example.pi_projet.entities.Invitation;
import com.example.pi_projet.services.IInvitationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Invitation")
public class InvitationController {
    IInvitationService invitationService;


    @PostMapping("/addInvitation/{idUser}/{idGroupe}")
    public Invitation addInvitation(@PathVariable Long idUser , @PathVariable Long idGroupe)
    {
          return invitationService.addInvitation(idUser,idGroupe);
    }

    @DeleteMapping("/deletePost/{idInvitation}")
    public void  deletePost(@PathVariable Long idInvitation)
    {
         invitationService.deleteInvitation(idInvitation);
    }

    @GetMapping("/retrieveAllInvitation/{idGroupe}")
    public List<Invitation> retrieveAll (@PathVariable Long idGroupe){
        List<Invitation> invitationList = invitationService.retrieveInvitations(idGroupe);
    return invitationList;
    }

}
