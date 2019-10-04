package ca.qc.bb.p55.georges.client.MainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ca.qc.bb.p55.georges.client.Client;
import ca.qc.bb.p55.georges.client.ClientAdapter;
import ca.qc.bb.p55.georges.client.R;

public class MainActivityModel {
    private AppCompatActivity mainActivity;
    private RecyclerView.LayoutManager  layoutManager;
    private ArrayList<Client> list;
    private RecyclerView recyclerView;
    private ClientAdapter adapter;

    public MainActivityModel(AppCompatActivity mainActivity) {
        this.mainActivity = mainActivity;

        layoutManager = new LinearLayoutManager(mainActivity);

        list = new ArrayList<>();
        list.add(new Client("Côté", "Georges", R.drawable.tyrion));
        list.add(new Client("Legault", "François", R.drawable.shrek));

        adapter = new ClientAdapter(list);

        recyclerView = mainActivity.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public ArrayList<Client> getList() {
        return list;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public ClientAdapter getAdapter() {
        return adapter;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }
}
