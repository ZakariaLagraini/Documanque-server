package com.documanque.documanqueserver.Commentaire;

import com.documanque.documanqueserver.Utilisateur.Utilisateur;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/commentaire")
public class CommentaireController {


    @Autowired
    private final CommentaireService commentaireService;

    public CommentaireController(CommentaireService commentaireService) {
        this.commentaireService = commentaireService;
    }

    @PostMapping(path = "/addComment", consumes = "application/json")
    public void addComment(@RequestBody String request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        AddComReq c = mapper.readValue(request, AddComReq.class);
        commentaireService.addComment(c.postedBy, c.postedOn, c.announcer, c.text, c.date);
    }

    @GetMapping(path = "/getCommentairesByAnnonce")
    public List<Commentaire> getCommentairesByAnnonce(@RequestParam("id") long id){
        return commentaireService.getCommentairesByAnnonce(id);
    }

}
