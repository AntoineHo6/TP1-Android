package ca.qc.bb.p55.georges.TP1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ca.qc.bb.p55.georges.client.R;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ClientViewHolder> {
    private ArrayList<MyList>   list;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onEditClick(int position);
    }

    public static class ClientViewHolder extends RecyclerView.ViewHolder {
        public TextView  tvNom;
        public ImageView ivEdit;

        public ClientViewHolder(View view, final OnItemClickListener listener) {
            super(view);

            tvNom = view.findViewById(R.id.nomDeFamille);
            ivEdit = view.findViewById(R.id.edit);

            ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        // la position est valide?
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onEditClick(position);
                        }
                    }
                }
            });
        }
    }

    public MyListAdapter(ArrayList<MyList> list) {
        this.list = list;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    @NonNull
    @Override
    public ClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_client, parent, false);
        ClientViewHolder ivh = new ClientViewHolder(v, listener);
        return ivh;
    }

    @Override
    public void onBindViewHolder(@NonNull ClientViewHolder holder, int position) {
        MyList myList = list.get(position);
        holder.tvNom.setText(myList.getNom());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
