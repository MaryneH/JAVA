package edu.fbansept.demo.controller;

import edu.fbansept.demo.dao.QuestionDao;
import edu.fbansept.demo.dao.ReponsePossibleDao;
import edu.fbansept.demo.model.Question;
import edu.fbansept.demo.model.ReponsePossible;
import edu.fbansept.demo.security.IsAdmin;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/reponse")
public class ReponsePossibleController {

    @Autowired
    ReponsePossibleDao reponsePossibleDao;

    @Autowired
    QuestionDao questionDao;

    @IsAdmin
    @PostMapping("/question/{questionId}")
    public ResponseEntity<ReponsePossible> addReponseToQuestion(
            @PathVariable int questionId,
            @RequestBody @Valid ReponsePossible reponsePossible) {

        Optional<Question> optionalQuestion = questionDao.findById(questionId);

        if(optionalQuestion.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Question question = optionalQuestion.get();

        if (question.getReponsePossible() != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        reponsePossible.setQuestion(question);
        reponsePossibleDao.save(reponsePossible);

        question.setReponsePossible(reponsePossible);
        questionDao.save(question);

        return new ResponseEntity<>(reponsePossible, HttpStatus.CREATED);
    }
}
