package ca.qc.bb.p55.georges.TP1.MyListsRecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ca.qc.bb.p55.georges.TP1.IOnItemClickListener;
import ca.qc.bb.p55.georges.TP1.MyListContract;
import ca.qc.bb.p55.georges.client.R;

public class MyListAdapter extends RecyclerView.Adapter<MyListViewHolder> {
    private IOnItemClickListener listener;

    private Context context;
    private Cursor cursor;

    public MyListAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
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
        if (!cursor.moveToPosition(position)) {
            return;
        }
        else {
            String name = cursor.getString(cursor.getColumnIndex(MyListContract.MyListEntry.COLUMN_NAME));
            long id = cursor.getLong(cursor.getColumnIndex(MyListContract.MyListEntry._ID));

            holder.tvNom.setText(name);
            holder.itemView.setTag(id);
        }
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (cursor != null) {
            cursor.close();
        }

        cursor = newCursor;

        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }
}
