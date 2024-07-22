package com.documanque.documanqueserver.Document;

public class CarteBancaire extends Document{

    public CarteBancaire() {
    }

    public CarteBancaire(String nomProprietaire, String nomBank) {
        super("Carte bancaire", "*****", nomProprietaire, nomBank, null, null, null);
    }

    public CarteBancaire(long id, String nomProprietaire, String nomBank) {
        super("Carte bancaire", "*****", nomProprietaire, nomBank, null, null, null);
    }

}
