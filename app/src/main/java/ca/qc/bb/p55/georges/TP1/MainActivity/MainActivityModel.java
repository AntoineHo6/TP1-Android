package ca.qc.bb.p55.georges.TP1.MainActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ca.qc.bb.p55.georges.TP1.MyListContract;
import ca.qc.bb.p55.georges.TP1.MyListDBHelper;
import ca.qc.bb.p55.georges.TP1.MyListsRecyclerView.*;
import ca.qc.bb.p55.georges.client.R;
import ca.qc.bb.p55.georges.TP1.MyListContract.*;

public class MainActivityModel {
    private AppCompatActivity mainActivity; // may be deprecated
    private RecyclerView.LayoutManager  layoutManager;
    private RecyclerView recyclerView;
    private MyListAdapter adapter;
    private SQLiteDatabase dataBase;
    private int currentSelectedListIdx;

    public MainActivityModel(AppCompatActivity mainActivity) {
        this.mainActivity = mainActivity;

        layoutManager = new LinearLayoutManager(mainActivity);

        MyListDBHelper dbHelper = new MyListDBHelper(mainActivity);
        dataBase = dbHelper.getWritableDatabase();

        adapter = new MyListAdapter(mainActivity, getAllLists());

        recyclerView = mainActivity.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        this.mainActivity = mainActivity;



    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public MyListAdapter getAdapter() {
        return adapter;
    }

    public SQLiteDatabase getDataBase() {
        return dataBase;
    }

    public int getCurrentSelectedListIdx() {
        return currentSelectedListIdx;
    }

    public void setCurrentSelectedListIdx(int currentSelectedListIdx) {
        this.currentSelectedListIdx = currentSelectedListIdx;
    }

    public void addListToDB(String name) {
            ContentValues cv = new ContentValues();
            cv.put(MyListContract.MyListEntry.COLUMN_NAME, name);
            dataBase.insert(MyListContract.MyListEntry.TABLE_NAME, null, cv);
            adapter.swapCursor(getAllLists());
    }

    private Cursor getAllLists() {
        return dataBase.query(
                MyListContract.MyListEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    public String getListItems(String listName) {
        Cursor c = dataBase.rawQuery("SELECT items FROM " + MyListContract.MyListEntry.TABLE_NAME + " WHERE name=" + "'" + listName + "'", null);
        c.moveToFirst();

        String listItems = c.getString(0);

        c.close();

        return listItems;
    }

    public void updateListsItems(String listName, String listItems) {
        ContentValues cv = new ContentValues();
        cv.put(MyListEntry.COLUMN_NAME, listName);

        if (listItems == null || listItems.isEmpty()) {
            cv.putNull(MyListEntry.COLUMN_ITEMS);
        }
        else {
            cv.put(MyListEntry.COLUMN_ITEMS, listItems);
        }

        dataBase.update(MyListEntry.TABLE_NAME, cv, "name="+"'" + listName + "'", null);
    }

    public void removeItem(long id) {
        dataBase.delete(MyListEntry.TABLE_NAME,
                MyListEntry._ID + "=" + id, null);
        adapter.swapCursor(getAllLists());
    }
}
