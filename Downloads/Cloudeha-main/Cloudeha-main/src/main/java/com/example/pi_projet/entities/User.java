package com.example.pi_projet.entities;




import jakarta.persistence.*;
import lombok.*;


//import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "Nom")
    private String nom;
    @Column(name = "Prenom")
    private String prenom;
    @Column(name = "Email")
    private String email;
    @Column(name = "Age")
    private String age;
    @Column(name = "MDP")
    private String MDP;
    @Column(name = "TDCEchouées")
    private int TDCEchouees;
    @Column(name = "telephone")
    private String tel;
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
    @OneToOne
    private Roles userRole;

    @OneToOne
    private Invitation invitation_user;
    @ManyToMany
    private Set<Groupe> groupeSet;
    @OneToMany(mappedBy = "user_post")
    private Set<Post> posts;

    private boolean locked = false;
    private boolean enabled = false;

    public User(String nom, String prenom, String email, String age, String MDP,  Roles userRole, String tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.age = age;
        this.MDP = MDP;
        this.userRole = userRole;
        this.tel = tel;

    }



}
