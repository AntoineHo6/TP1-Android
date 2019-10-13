package ca.qc.bb.p55.georges.TP1.MyListsRecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class MyList implements Serializable {

    private String nom;
    private List<String> listItems;

    public MyList(String nom) {
        this.nom = nom;
        this.listItems = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }


    public List<String> getlistItems() {
        return listItems;
    }
}