package com.example.pi_projet.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.pi_projet.entities.RendezVous;
import com.example.pi_projet.services.IRendezVousService;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("RendezVous")
@CrossOrigin
public class RendezVousController {
    IRendezVousService rendezVousService;

    @GetMapping("/getAllRdv")
    public List<RendezVous> getListRdv(){
         List<RendezVous>   listRdv = (List<RendezVous>) rendezVousService.recupererAllRdv();
    return listRdv;
    }

    @GetMapping("/getRdv/{idRdv}")
    public RendezVous getRdv(@PathVariable Long idRdv){
      RendezVous  Rdv = rendezVousService.recupererRdv(idRdv);
        return Rdv;
    }

    @PostMapping("/addRdv")
    public RendezVous addRdv(@RequestBody RendezVous rendezVous){
        return rendezVousService.addRDV(rendezVous);
    }

    @PostMapping("/AssignRdvToGroupe/{idRdv}/{idGroupe}")

    public RendezVous AssignRdvToGroupe(@PathVariable Long idRdv , @PathVariable Long idGroupe){
        return  rendezVousService.AssignRdvToGroupe(idRdv,idGroupe);
    }


    @PutMapping("/updateRdv")
    public RendezVous upddateRdv(@RequestBody RendezVous rendezVous){
        return rendezVousService.updateRDV(rendezVous);
    }

    @PutMapping("/AssignPointsToRendezVoous/{idRdv}/{points}")
    public RendezVous AssignPointsToRendezVoous(@PathVariable Long idRdv, @PathVariable float points){
        return rendezVousService.AssignPointsToRendezVoous(idRdv, points);
    }


    @DeleteMapping("/deleteRdv/{idRdv}")
    public void deleteRdv (@PathVariable Long idRdv){
        rendezVousService.deleteRDV(idRdv);
    }


    @GetMapping("/ListeRdvByDate/{date}")
    public List<RendezVous> listrdv(@PathVariable LocalDate date){
        List<RendezVous>  listrdv = (List<RendezVous>) rendezVousService.listeRdvByDate(date);
        return listrdv;
    }

    @GetMapping("/listRdvBetweenDates/{dateDeb}/{dateFin}")
    public List<RendezVous> rendezVousList(@PathVariable LocalDate dateDeb , @PathVariable LocalDate dateFin){
        List<RendezVous> RdvsList = (List<RendezVous>) rendezVousService.ListRdvBetweenDates(dateDeb,dateFin);
        return RdvsList;
    }

    @GetMapping("/listeRdvByGroupe/{idGroupe}")
    public List<RendezVous> rendezVousByGroupeList(@PathVariable Long idGroupe) {
        List<RendezVous> rdvbygrp = (List<RendezVous>) rendezVousService.LISTRdvByGroupe(idGroupe);
        return  rdvbygrp;
    }

    @GetMapping("/heuresDisponibles/{date}")
    public List<String> heuresDisponibles(@PathVariable String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        return rendezVousService.heuresDisponibles(parsedDate);
    }


}
