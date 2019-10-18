package ca.qc.bb.p55.georges.TP1.ActivityShowListItems;

import androidx.appcompat.app.AppCompatActivity;

import ca.qc.bb.p55.georges.TP1.MyItemsRecyclerView.MyItemAdapter;

public class ActivityShowListItemsView {

    private AppCompatActivity ActivityShowListItems;

    public ActivityShowListItemsView (AppCompatActivity ActivityShowListItems) {
        this.ActivityShowListItems = ActivityShowListItems;
    }

    public void updateItemsInterface(MyItemAdapter adapter) {
        adapter.notifyItemInserted(adapter.getItemCount() - 1);
        adapter.notifyDataSetChanged();
    }
}
