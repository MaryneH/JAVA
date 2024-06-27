package edu.fbansept.demo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ReponseUtilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @OneToOne(optional = false)
    @JoinColumn(name = "utilisateur_id", unique = true)
    protected Utilisateur utilisateur;

    @OneToOne(optional = false)
    @JoinColumn(name = "reponse_possible_id", unique = true)
    protected ReponsePossible reponsePossible;
}