package com.documanque.documanqueserver.Document;

public class Autre extends Document {
    public Autre() {
    }

    public Autre(String nomDocument, String numDocument, String nomProprietaire) {
        super(nomDocument, numDocument, nomProprietaire, null, null, null, null);
    }

    public Autre(long id, String nomDocument, String numDocument, String nomProprietaire) {
        super(id, nomDocument, numDocument, nomProprietaire, null, null, null, null);
    }

}
