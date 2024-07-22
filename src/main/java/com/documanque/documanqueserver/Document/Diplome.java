package com.documanque.documanqueserver.Document;

import java.util.Date;

public class Diplome extends Document{

    public Diplome() {
    }

    public Diplome(String numDocument, String nomProprietaire, Date dateNaissance, String typeDiplome) {
        super("Diplome", numDocument, nomProprietaire, null, dateNaissance, null, typeDiplome);
    }

    public Diplome(long id, String numDocument, String nomProprietaire, Date dateNaissance, String typeDiplome) {
        super(id,"Diplome", numDocument, nomProprietaire, null, dateNaissance, null, typeDiplome);
    }

}
