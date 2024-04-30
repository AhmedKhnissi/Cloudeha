package com.example.pi_projet.repositories;


import com.example.pi_projet.entities.Invitation;
import com.example.pi_projet.entities.Role;
import com.example.pi_projet.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
     User findUserById(Long idUSer);
     List<User> findUsersByUserRoleRole(Role role);

     @Query("SELECT u.id FROM User u WHERE u.id = :idUser")
     List<User> search(@Param("idUser") Long idUser);


}
