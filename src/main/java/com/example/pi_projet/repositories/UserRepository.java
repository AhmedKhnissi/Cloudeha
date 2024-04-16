package com.example.pi_projet.repositories;


import com.example.pi_projet.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
     User findUserById(Long idUSer);
}
