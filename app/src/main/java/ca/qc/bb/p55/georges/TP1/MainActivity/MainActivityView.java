package ca.qc.bb.p55.georges.TP1.MainActivity;

import androidx.appcompat.app.AppCompatActivity;

import ca.qc.bb.p55.georges.TP1.MyListsRecyclerView.MyListAdapter;

public class MainActivityView {

    private AppCompatActivity mainActivity;

    public MainActivityView(AppCompatActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void updateListsInterface(MyListAdapter adapter) {
        adapter.notifyItemInserted(adapter.getItemCount() - 1);
        adapter.notifyDataSetChanged();
    }
}
