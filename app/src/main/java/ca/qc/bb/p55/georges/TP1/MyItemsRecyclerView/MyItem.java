package ca.qc.bb.p55.georges.TP1.MyItemsRecyclerView;


import java.io.Serializable;

public class MyItem implements Serializable {

    private String info;

    public MyItem(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

}