package com.documanque.documanqueserver.Document;

import java.util.Date;

public class CinPermisPasseport extends Document{
    public CinPermisPasseport() {
    }

    public CinPermisPasseport(String nomDocument, String nomProprietaire, String numDocument, Date dateNaissance, String lieuNaissance) {
        super(nomDocument, numDocument, nomProprietaire, null, dateNaissance, lieuNaissance, null);
    }

    public CinPermisPasseport(long id, String nomDocument, String nomProprietaire, String numDocument, Date dateNaissance, String lieuNaissance) {
        super(id, nomDocument, numDocument, nomProprietaire, null, dateNaissance, lieuNaissance, null);
    }
}
