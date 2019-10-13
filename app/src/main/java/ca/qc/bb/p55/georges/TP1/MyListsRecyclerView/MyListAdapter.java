package ca.qc.bb.p55.georges.TP1.MyListsRecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ca.qc.bb.p55.georges.TP1.IOnItemClickListener;
import ca.qc.bb.p55.georges.client.R;

public class MyListAdapter extends RecyclerView.Adapter<MyListViewHolder> {
    private ArrayList<MyList>   list;
    private IOnItemClickListener listener;

    public MyListAdapter(ArrayList<MyList> list) {
        this.list = list;
    }

    public void setOnItemClickListener(IOnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_mylists, parent, false);
        MyListViewHolder ivh = new MyListViewHolder(v, listener);
        return ivh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyListViewHolder holder, int position) {
        MyList myList = list.get(position);
        holder.tvNom.setText(myList.getNom());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
