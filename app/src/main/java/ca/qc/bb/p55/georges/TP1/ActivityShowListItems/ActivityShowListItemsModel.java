package ca.qc.bb.p55.georges.TP1.ActivityShowListItems;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ca.qc.bb.p55.georges.TP1.MyItemsRecyclerView.MyItem;
import ca.qc.bb.p55.georges.TP1.MyItemsRecyclerView.MyItemAdapter;
import ca.qc.bb.p55.georges.TP1.MyListsRecyclerView.MyList;
import ca.qc.bb.p55.georges.client.R;

public class ActivityShowListItemsModel {
    private AppCompatActivity activityShowItems;    // may be deprecated
    private RecyclerView.LayoutManager  layoutManager;
    private MyItemAdapter adapter;
    private MyList myList;
    private RecyclerView recyclerView;

    public ActivityShowListItemsModel(AppCompatActivity activityShowItems, MyList myList) {
        this.activityShowItems = activityShowItems;

        layoutManager = new LinearLayoutManager(activityShowItems);

        this.myList = myList;

        adapter = new MyItemAdapter(myList.getlistItems());

        recyclerView = activityShowItems.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public ArrayList<MyItem> getList() {
        return myList.getlistItems();
    }

    public MyItemAdapter getAdapter() {
        return adapter;
    }
}
