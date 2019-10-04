package ca.qc.bb.p55.georges.client;

import java.util.Comparator;

public class Client {
    private String nomDeFamille;
    private String prenom;
    private int    idImage;
    private boolean estApprecie;

    public Client(String nomDeFamille, String prenom, int idImage) {
        this.nomDeFamille = nomDeFamille;
        this.prenom = prenom;
        this.idImage = idImage;
        estApprecie = false;
    }

    public String getNom() {
        return nomDeFamille;
    }
    public String getPrenom() {
        return prenom;
    }
    public int getImage() {
        return idImage;
    }
}