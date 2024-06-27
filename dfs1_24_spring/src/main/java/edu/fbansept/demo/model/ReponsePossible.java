package edu.fbansept.demo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ReponsePossible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @NotBlank
    @NotNull
    @Column(columnDefinition = "TEXT")
    protected String texte;

    @NotNull
    protected boolean estVrai;

    @OneToOne(optional = false)
    @JoinColumn(name = "question_id")
    protected Question question;

    @OneToOne(mappedBy = "reponsePossible", fetch = FetchType.EAGER)
    protected ReponseUtilisateur reponseUtilisateur;
}
