package com.documanque.documanqueserver.Annonce;

import com.documanque.documanqueserver.Document.*;
import com.documanque.documanqueserver.Utilisateur.Utilisateur;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AnnonceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AnnonceService annonceService;

    @InjectMocks
    private AnnonceController annonceController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(annonceController).build();
    }

    @Test
    public void testGetAnnoncesByUser() throws Exception {
        Annonce annonce = new Annonce(1, 1, new Document(), new Utilisateur(), new Date());
        List<Annonce> annonces = Collections.singletonList(annonce);

        when(annonceService.getAnnoncesByUser(anyString())).thenReturn(annonces);

        mockMvc.perform(get("/annonce/getAnnoncesByUser")
                        .param("user", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    public void testGetAnnoncesBySearch() throws Exception {
        Annonce annonce = new Annonce(1, 1, new Document(), new Utilisateur(), new Date());
        List<Annonce> annonces = Collections.singletonList(annonce);

        when(annonceService.getAnnoncesBySearch(anyString())).thenReturn(annonces);

        mockMvc.perform(get("/annonce/getAnnoncesBySearch")
                        .param("search", "test")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }


    @Test
    public void testPostAnonymousAnnonce() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        RequestIntermediate request = new RequestIntermediate();
        request.typeAnnonce = 1;
        request.nomDocument = "CIN";
        request.nomDocumentAtr = "document";
        request.numDocument = "123456";
        request.nomProprietaire = "owner";
        request.dateNaissance = new Date();
        request.lieuNaissance = "place";
        request.nomBank = "bank";
        request.typeDiplome = "diploma";
        request.idAnnouncer = 1L;
        request.nomAnnouncer = "announcer";
        request.prenomAnnouncer = "announcer";
        request.telAnnouncer = "123456789";
        request.emailAnnouncer = "email@example.com";
        request.publishingTime = new Date();

        String jsonRequest = mapper.writeValueAsString(request);

        when(annonceService.checkUtilisateurExists(anyString(), anyString())).thenReturn(null);
        when(annonceService.addAnonymousAnnonce(any(Annonce.class))).thenReturn("{\"id\" :\"1\"}");

        mockMvc.perform(post("/annonce/postAnonymousAnnonce")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\" :\"1\"}"));
    }

    @Test
    public void testPostAnnonce() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        RequestIntermediate request = new RequestIntermediate();
        request.typeAnnonce = 1;
        request.nomDocument = "CIN";
        request.nomDocumentAtr = "document";
        request.numDocument = "123456";
        request.nomProprietaire = "owner";
        request.dateNaissance = new Date();
        request.lieuNaissance = "place";
        request.nomBank = "bank";
        request.typeDiplome = "diploma";
        request.idAnnouncer = 1L;
        request.nomAnnouncer = "announcer";
        request.prenomAnnouncer = "announcer";
        request.telAnnouncer = "123456789";
        request.emailAnnouncer = "email@example.com";
        request.publishingTime = new Date();

        String jsonRequest = mapper.writeValueAsString(request);

        when(annonceService.getUserById(anyLong())).thenReturn(new Utilisateur());
        when(annonceService.addAnonymousAnnonce(any(Annonce.class))).thenReturn("{\"id\" :\"1\"}");

        mockMvc.perform(post("/annonce/postAnnonce")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\" :\"1\"}"));
    }

    @Test
    public void testDeleteAnnonce() throws Exception {
        when(annonceService.deleteAnnonce(anyLong(), anyLong())).thenReturn("{\"status\":\"success\"}");

        mockMvc.perform(delete("/annonce/deleteAnnonce")
                        .param("idannonce", "1")
                        .param("iduser", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"status\":\"success\"}"));
    }
}
