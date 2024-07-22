package com.documanque.documanqueserver.Commentaire;

import com.documanque.documanqueserver.Annonce.Annonce;
import com.documanque.documanqueserver.Utilisateur.Utilisateur;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CommentaireControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CommentaireService commentaireService;

    @InjectMocks
    private CommentaireController commentaireController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(commentaireController).build();
    }


    @Test
    public void testGetCommentairesByAnnonce() throws Exception {
        long annonceId = 1L;
        Commentaire commentaire = new Commentaire(new Utilisateur(), new Annonce(), "Test comment", new Date());
        List<Commentaire> commentaireList = Collections.singletonList(commentaire);

        when(commentaireService.getCommentairesByAnnonce(annonceId)).thenReturn(commentaireList);

        mockMvc.perform(MockMvcRequestBuilders.get("/commentaire/getCommentairesByAnnonce")
                        .param("id", String.valueOf(annonceId)))
                .andExpect(status().isOk());

        verify(commentaireService).getCommentairesByAnnonce(annonceId);
    }
}
