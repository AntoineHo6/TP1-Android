package ca.qc.bb.p55.georges.TP1.MyItemsRecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ca.qc.bb.p55.georges.TP1.IOnItemClickListener;
import ca.qc.bb.p55.georges.client.R;

public class MyItemAdapter extends RecyclerView.Adapter<MyItemViewHolder> {
    private ArrayList<MyItem>   list;
    private IOnItemClickListener listener;

    public MyItemAdapter(ArrayList<MyItem> list) {
        this.list = list;
    }

    public void setOnItemClickListener(IOnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_mylists, parent, false);
        MyItemViewHolder ivh = new MyItemViewHolder(v, listener);
        return ivh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyItemViewHolder holder, int position) {
        MyItem myItem = list.get(position);
        holder.tvNom.setText(myItem.getInfo());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
