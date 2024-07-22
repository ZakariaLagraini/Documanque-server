package com.documanque.documanqueserver.Utilisateur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    @Query(value="select * from utilisateur u where u.email = :email and u.mot_de_passe = :password", nativeQuery = true)
    Utilisateur findUtilisateurByEmailPassword(@Param("email") String email, @Param("password") String password);

    @Query(value="select * from utilisateur u where u.email = :email", nativeQuery = true)
    Utilisateur findUtilisateurByEmail(@Param("email") String email);

    @Query(value="select * from utilisateur u where u.tel = :tel", nativeQuery = true)
    Utilisateur findUtilisateurByTel(@Param("tel") String tel);

    @Query(value="select * from utilisateur u where u.id = :id and u.mot_de_passe = :password", nativeQuery = true)
    Utilisateur findUtilisateurByIdPassword(@Param("id") long id, @Param("password") String password);

    @Query(value="SELECT * FROM utilisateur u WHERE u.id = :id", nativeQuery = true)
    Utilisateur getUtilisateurById(@Param("id") long id);

    @Query(value="SELECT * FROM utilisateur WHERE token = :token", nativeQuery = true)
    Utilisateur getUtilisateurByVerificationToken(@Param("token") String token);

    @Query(value="SELECT token FROM utilisateur WHERE id = :id", nativeQuery = true)
    String getVerificationToken(@Param("id") long id);
}
