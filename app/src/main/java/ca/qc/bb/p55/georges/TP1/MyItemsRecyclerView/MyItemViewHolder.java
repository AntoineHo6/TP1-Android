package ca.qc.bb.p55.georges.TP1.MyItemsRecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import ca.qc.bb.p55.georges.TP1.IOnItemClickListener;
import ca.qc.bb.p55.georges.client.R;

public class MyItemViewHolder extends RecyclerView.ViewHolder {
    public TextView tvNom;

    public MyItemViewHolder(View view, final IOnItemClickListener listener) {
        super(view);

        tvNom = view.findViewById(R.id.nomDeFamille);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // vérifier que le listener n'est pas null
                if (listener != null) {
                    int position = getAdapterPosition();
                    // la position est valide?
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            }
        });
    }
}
