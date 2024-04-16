package com.example.pi_projet.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.pi_projet.entities.Groupe;
import com.example.pi_projet.entities.Option;
import com.example.pi_projet.services.IGroupeService;

import java.time.Year;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/Groupe")
public class GroupeController {
    IGroupeService groupeService;


    @GetMapping("/getAllGroupes")
    public List<Groupe> getAllGroupes(){
        List<Groupe> listGroupes = (List<Groupe>) groupeService.recupererAllGroupes();
        return listGroupes;
    }

    @GetMapping("/getGroupe/{idGroupe}")
    public Groupe getGroupe(@PathVariable Long idGroupe){
        Groupe grp = groupeService.recupererGroupe(idGroupe);
        return  grp;
    }

    @PostMapping("/addGroupe")
    public Groupe addGroupe(@RequestBody Groupe groupe) {
        return groupeService.addGroupe(groupe);
    }

    @PutMapping("/updateGroupe")
    public  Groupe updateGroupe(@RequestBody Groupe groupe){
        return groupeService.updateGroupe(groupe);
    }

    @DeleteMapping("/deleteGroupe/{idGroupe}")
    public void deleteGroupe(@PathVariable Long idGroupe) {
        groupeService.deleteGroupe(idGroupe);
    }

    @GetMapping("/listGroupeByOptions/{option}")
    public List<Groupe> listGroupeOption (@PathVariable Option option){
        return groupeService.listeGroupesByOptions(option);
    }

    @GetMapping("/listGroupeByAnnee/{annee_scolaire}")
    public List<Groupe> listeGroupeAnne (@PathVariable Year annee_scolaire){
        return groupeService.ListGroupeByAnneScolaireI(annee_scolaire);
    }

    @PostMapping("/AssignUserTOGroupe/{idUser}/{idGroupe}")
    public Groupe AssignUserTOGroupe(@PathVariable Long idUser, @PathVariable Long idGroupe){
        return groupeService.AssignUserTOGroupe(idUser,idGroupe);
    }

}
