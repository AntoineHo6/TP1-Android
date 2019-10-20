package ca.qc.bb.p55.georges.TP1.ActivityShowListItems;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ca.qc.bb.p55.georges.TP1.MyItemsRecyclerView.MyItemAdapter;
import ca.qc.bb.p55.georges.client.R;

public class ActivityShowListItemsView {

    private AppCompatActivity ActivityShowListItems;

    public ActivityShowListItemsView (AppCompatActivity ActivityShowListItems) {
        this.ActivityShowListItems = ActivityShowListItems;
    }

    public void updateItemsInterface(MyItemAdapter adapter) {
        adapter.notifyItemInserted(adapter.getItemCount() - 1);
        adapter.notifyDataSetChanged();
    }

    public void changeListName(String name) {
        ((TextView) ActivityShowListItems.findViewById(R.id.txtViewListName)).setText(name);
    }
}
