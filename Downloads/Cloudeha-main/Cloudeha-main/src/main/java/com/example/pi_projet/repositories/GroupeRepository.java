package com.example.pi_projet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.pi_projet.entities.Groupe;
import com.example.pi_projet.entities.Option;

import java.time.Year;
import java.util.List;

@Repository
public interface GroupeRepository extends JpaRepository<Groupe,Long> {
    List<Groupe> findGroupeByOption(Option option);
    @Query("SELECT g FROM Groupe g WHERE g.annee = :year")
    List<Groupe> SelectPerYear(@Param("year") Year year);

    Groupe findGroupesByIdGroupe(Long idGroupe);


}
