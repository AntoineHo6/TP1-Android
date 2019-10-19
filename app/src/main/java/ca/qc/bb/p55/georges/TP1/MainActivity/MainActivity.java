package ca.qc.bb.p55.georges.TP1.MainActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import ca.qc.bb.p55.georges.TP1.ActivityAddObject.ActivityAddObject;
import ca.qc.bb.p55.georges.TP1.ActivityShowListItems.ActivityShowListItems;
import ca.qc.bb.p55.georges.TP1.IOnItemClickListener;
import ca.qc.bb.p55.georges.TP1.MyListContract;
import ca.qc.bb.p55.georges.TP1.MyListDBHelper;
import ca.qc.bb.p55.georges.TP1.MyListsRecyclerView.MyList;
import ca.qc.bb.p55.georges.client.R;


public class MainActivity extends AppCompatActivity {

    MainActivityModel mainActivityModel;
    MainActivityView mainActivityView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityModel = new MainActivityModel(this);
        mainActivityView = new MainActivityView(this);

        mainActivityModel.getAdapter().setOnItemClickListener(new IOnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                openActivityShowListItems(position);
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {

                String nom = data.getStringExtra("nom");
                if (nom != null) {
                    addList(nom);
                }

                MyList myNewList = (MyList) data.getSerializableExtra("myList");
                if (myNewList != null) {
                    int listIndex = mainActivityModel.getIndexCurrentViewedSubList();
//                    mainActivityModel.getList().set(listIndex, myNewList);
//                    mainActivityModel.addItemsToDB();
                    // TODO: it returns strings
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.addMenu) {
            openActivityAddList();
        }

        return super.onOptionsItemSelected(item);
    }

    private void openActivityAddList() {
        Intent intent = new Intent(this, ActivityAddObject.class);
        intent.putExtra("objectType", "list");
        startActivityForResult(intent, 1);
    }

    private void addList(String nom) {
//        mainActivityModel.getList().add(new MyList(nom));
//        mainActivityModel.sortMyLists();

        mainActivityModel.addListToDB(nom);

        mainActivityView.updateListsInterface(mainActivityModel.getAdapter());
    }

    private void openActivityShowListItems(int position) {
        Intent intent = new Intent(this, ActivityShowListItems.class);

        mainActivityModel.setIndexCurrentViewedSubList(position);

//        MyList myList = mainActivityModel.getList().get(position);
//        intent.putExtra("myList", myList);
        startActivityForResult(intent, 1);
    }



}



/*
    TODO: 1. Rename ActivityAddObject to ActivityAddList
          2. Split ActivityAddList to MVC style
          3. Check for returned strings not only null but also check if isEmpty() and trim() them.
          4. Make the mainActivity's view actually do something
          5. Add view to ActivityShowItems
          6. Remove auto-caps for "nom"
          7. Swipe to delete implementation
          8. save on sql thingy
          9. use stringbuilder to save items
 */