package ca.qc.bb.p55.georges.TP1.ActivityShowListItems;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ca.qc.bb.p55.georges.TP1.MyItemsRecyclerView.MyItemAdapter;
import ca.qc.bb.p55.georges.client.R;

public class ActivityShowListItemsModel {
    private AppCompatActivity activityShowItems;    // may be deprecated
    private RecyclerView.LayoutManager  layoutManager;
    private MyItemAdapter adapter;
    private RecyclerView recyclerView;
    private String listName;
    private String tokenizedItems;

    public ActivityShowListItemsModel(AppCompatActivity activityShowItems, String listName, String items) {
        this.activityShowItems = activityShowItems;

        layoutManager = new LinearLayoutManager(activityShowItems);

        this.listName = listName;
        this.tokenizedItems = items;

        adapter = new MyItemAdapter(getDetokenizedItems());

        recyclerView = activityShowItems.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public MyItemAdapter getAdapter() {
        return adapter;
    }

    public String getTokenizedItems() {
        return tokenizedItems;
    }

    public String getListName() {
        return listName;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    private ArrayList<String> getDetokenizedItems() {
        if (tokenizedItems == null) {
            return new ArrayList<>();
        }

        ArrayList<String> detokenizedItems = new ArrayList<>();
        String[] parts = tokenizedItems.split(";");

        for (int i = 0; i < parts.length ; i++) {
            detokenizedItems.add(parts[i]);
        }

        return detokenizedItems;
    }

    public void updateTokenizedItems() {
        tokenizedItems = "";
        ArrayList<String> adapterList = adapter.getList();

        for (int i = 0; i < adapterList.size(); i++) {
            tokenizedItems += adapterList.get(i) + ";";
        }
    }

    public void addItemToAdapter(String content) {
        adapter.getList().add(content);
    }

    public void removeItem(int position) {
        adapter.getList().remove(position);
        adapter.notifyDataSetChanged();
    }

    public String getFormattedItemsForEmail() {
        String[] parts = tokenizedItems.split(";");
        String formattedItems = "";

        for (int i = 0; i < parts.length; i++) {
            formattedItems += "- " + parts[i] + "\n";
        }

        return formattedItems;
    }
}
