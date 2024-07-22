package com.documanque.documanqueserver.Annonce;

import com.documanque.documanqueserver.Utilisateur.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce, Long> {

    @Query(value="SELECT * FROM annonce WHERE posted_by_id = :user ORDER BY id DESC", nativeQuery = true)
    List<Annonce> getAnnoncesByUser(@Param("user") long userId);


    @Query(value="SELECT a FROM annonce a JOIN document d ON d = a.document WHERE d.nomDocument LIKE CONCAT('%', :search, '%') OR d.nomProprietaire LIKE CONCAT('%', :search, '%') OR d.numDocument LIKE CONCAT('%', :search, '%')")
    List<Annonce> getAnnoncesBySearch(@Param("search") String search);

    @Query(value="SELECT * FROM annonce WHERE id = :id", nativeQuery = true)
    Annonce getAnnonceById(@Param("id") long id);

    @Query(value="SELECT * FROM utilisateur WHERE email = :email OR tel = :tel", nativeQuery = true)
    Object checkUtilisateurExists(@Param("email") String email, @Param("tel") String tel);

    @Query(value="SELECT u FROM utilisateur u WHERE u.id = :idAnnouncer")
    Utilisateur getUserById(@Param("idAnnouncer") long idAnnouncer);

    @Modifying
    @Query(value="DELETE FROM annonce WHERE id = :id")
    void deleteAnnonce(@Param("id") long id);

    @Modifying
    @Query(value = "DELETE FROM commentaire WHERE posted_on_id = :id")
    void deleteAnnonceCommentaires(@Param("id") long idAnnonce);
}
