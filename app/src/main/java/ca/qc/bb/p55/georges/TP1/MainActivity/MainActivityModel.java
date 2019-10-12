package ca.qc.bb.p55.georges.TP1.MainActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ca.qc.bb.p55.georges.TP1.MyList;
import ca.qc.bb.p55.georges.TP1.MyListAdapter;
import ca.qc.bb.p55.georges.client.R;

public class MainActivityModel {
    private AppCompatActivity mainActivity;
    private RecyclerView.LayoutManager  layoutManager;
    private ArrayList<MyList> list;
    private RecyclerView recyclerView;
    private MyListAdapter adapter;

    public MainActivityModel(AppCompatActivity mainActivity) {
        this.mainActivity = mainActivity;

        layoutManager = new LinearLayoutManager(mainActivity);

        list = new ArrayList<>();
        list.add(new MyList("List 1"));
        list.add(new MyList("List 2"));

        adapter = new MyListAdapter(list);

        recyclerView = mainActivity.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public ArrayList<MyList> getList() {
        return list;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public MyListAdapter getAdapter() {
        return adapter;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }
}