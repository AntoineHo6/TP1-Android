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
//    private ArrayList<MyList> list;
    private RecyclerView recyclerView;
    private MyListAdapter adapter;
    private SQLiteDatabase dataBase;

    public MainActivityModel(AppCompatActivity mainActivity) {
        this.mainActivity = mainActivity;

        layoutManager = new LinearLayoutManager(mainActivity);

//        list = new ArrayList<>();
//        list.add(new MyList("List 1"));
//        list.add(new MyList("List 2"));

        MyListDBHelper dbHelper = new MyListDBHelper(mainActivity);
        dataBase = dbHelper.getWritableDatabase();

        adapter = new MyListAdapter(mainActivity, getAllLists());

        recyclerView = mainActivity.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        this.mainActivity = mainActivity;



    }

//    public ArrayList<MyList> getList() {
//        return list;
//    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public MyListAdapter getAdapter() {
        return adapter;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

//    public void sortMyLists() {
//        Collections.sort(list, new Comparator<MyList>() {
//            @Override
//            public int compare(MyList o1, MyList o2) {
//                return o1.getNom().compareTo(o2.getNom());
//            }
//        });
//    }

    public void addListToDB(String name) {
            ContentValues cv = new ContentValues();
            cv.put(MyListContract.MyListEntry.COLUMN_NAME, name);
            //cv.put(MyListContract.MyListEntry.COLUMN_ITEMS, items);

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
        cv.put(MyListEntry.COLUMN_ITEMS, listItems);

        dataBase.update(MyListEntry.TABLE_NAME, cv, "name="+"'" + listName + "'", null);
    }

    public void removeItem(long id) {
        dataBase.delete(MyListEntry.TABLE_NAME,
                MyListEntry._ID + "=" + id, null);
        adapter.swapCursor(getAllLists());
    }
}
