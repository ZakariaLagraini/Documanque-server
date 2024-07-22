package com.documanque.documanqueserver.Utilisateur;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.mail.MessagingException;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(UtilisateurController.class)
public class UtilisateurControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean // Use @MockBean for Spring integration
    private UtilisateurService utilisateurService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }


    @Test
    void testGetUtilisateurByEmailPassword() throws Exception {
        Utilisateur utilisateur = new Utilisateur("John", "Doe", "john@example.com", "1234567890", "password", "token", new Date());
        String utilisateurJson = objectMapper.writeValueAsString(utilisateur);

        when(utilisateurService.getUtilisateurByEmailPassword(anyString(), anyString())).thenReturn(utilisateurJson);

        mockMvc.perform(post("/utilisateur/logIn")
                        .contentType("application/json")
                        .content(utilisateurJson))
                .andExpect(status().isOk());
    }

    @Test
    void testSaveUtilisateur() throws Exception {
        Utilisateur utilisateur = new Utilisateur("John", "Doe", "john@example.com", "1234567890", "password", "token", new Date());
        String utilisateurJson = objectMapper.writeValueAsString(utilisateur);

        when(utilisateurService.getUtilisateurByEmail(anyString())).thenReturn(null);
        when(utilisateurService.getUtilisateurByTel(anyString())).thenReturn(null);
        when(utilisateurService.checkVerificationTokenExists(anyString())).thenReturn(null);
        when(utilisateurService.saveUtilisateur(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), any(Date.class)))
                .thenReturn("{\"id\" :\"1\"}");

        mockMvc.perform(post("/utilisateur/signUp")
                        .contentType("application/json")
                        .content(utilisateurJson))
                .andExpect(status().isOk());
    }

    @Test
    void testModifyInfo() throws Exception {
        Utilisateur utilisateur = new Utilisateur(1L, "John", "Doe", "john@example.com", "1234567890", "password", true, "token");
        String utilisateurJson = objectMapper.writeValueAsString(utilisateur);

        when(utilisateurService.getVerificationToken(any(Long.class))).thenReturn("token");
        when(utilisateurService.modifyInfo(any(Long.class), anyString(), anyString(), anyString(), anyString(), any(Boolean.class), anyString(), anyString()))
                .thenReturn("{\"id\" :\"1\"}");

        mockMvc.perform(put("/utilisateur/modifyInfo") // Use put() for PUT request
                        .contentType("application/json")
                        .content(utilisateurJson))
                .andExpect(status().isOk());
    }

    @Test
    void testVerifyUser() throws Exception {
        when(utilisateurService.verifyUser(anyString())).thenReturn("{\"id\" :\"1\"}");

        mockMvc.perform(get("/utilisateur/verify")
                        .param("token", "token"))
                .andExpect(status().isOk());
    }
}
