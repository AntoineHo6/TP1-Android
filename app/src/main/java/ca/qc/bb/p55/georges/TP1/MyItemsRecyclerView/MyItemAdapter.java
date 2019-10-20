package ca.qc.bb.p55.georges.TP1.MyItemsRecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ca.qc.bb.p55.georges.client.R;

public class MyItemAdapter extends RecyclerView.Adapter<MyItemViewHolder> {
    private ArrayList<String> list;

    public MyItemAdapter(ArrayList<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_myitems, parent, false);
        MyItemViewHolder ivh = new MyItemViewHolder(v);
        return ivh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyItemViewHolder holder, int position) {
        String myItem = list.get(position);
        holder.tvNom.setText(myItem);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public ArrayList<String> getList() {
        return list;
    }
}
