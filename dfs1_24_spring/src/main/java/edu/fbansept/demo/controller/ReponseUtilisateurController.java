package edu.fbansept.demo.controller;

import edu.fbansept.demo.dao.ReponseUtilisateurDao;
import edu.fbansept.demo.dao.ReponsePossibleDao;
import edu.fbansept.demo.model.ReponseUtilisateur;
import edu.fbansept.demo.model.ReponsePossible;
import edu.fbansept.demo.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/reponse-utilisateur")
public class ReponseUtilisateurController {

    @Autowired
    ReponseUtilisateurDao reponseUtilisateurDao;

    @Autowired
    ReponsePossibleDao reponsePossibleDao;

    @PostMapping("")
    public ResponseEntity<ReponseUtilisateur> addReponseUtilisateur(
            @RequestBody ReponsePossible reponsePossible,
            @AuthenticationPrincipal Utilisateur utilisateur) {

        Optional<ReponsePossible> reponsePossibleOptional = reponsePossibleDao.findById(reponsePossible.getId());

        if (reponsePossibleOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ReponseUtilisateur nouvelleReponse = new ReponseUtilisateur();
        nouvelleReponse.setUtilisateur(utilisateur);
        nouvelleReponse.setReponsePossible(reponsePossibleOptional.get());

        reponseUtilisateurDao.save(nouvelleReponse);

        return new ResponseEntity<>(nouvelleReponse, HttpStatus.CREATED);
    }
}
