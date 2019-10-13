package ca.qc.bb.p55.georges.TP1.MyListsRecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

import ca.qc.bb.p55.georges.TP1.MyItemsRecyclerView.MyItem;

@SuppressWarnings("serial")
public class MyList implements Serializable {

    private String nom;
    private ArrayList<MyItem> listItems;

    public MyList(String nom) {
        this.nom = nom;
        this.listItems = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }


    public ArrayList<MyItem> getlistItems() {
        return listItems;
    }
}