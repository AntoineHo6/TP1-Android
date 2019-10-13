package ca.qc.bb.p55.georges.TP1.MyItemsRecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import ca.qc.bb.p55.georges.TP1.IOnItemClickListener;
import ca.qc.bb.p55.georges.client.R;

public class MyItemViewHolder extends RecyclerView.ViewHolder {
    public TextView tvNom;

    public MyItemViewHolder(View view) {
        super(view);

        tvNom = view.findViewById(R.id.nomDeFamille);

    }
}
