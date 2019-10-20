package ca.qc.bb.p55.georges.TP1.MainActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import ca.qc.bb.p55.georges.TP1.ActivityAddObject.ActivityAddObject;
import ca.qc.bb.p55.georges.TP1.ActivityShowListItems.ActivityShowListItems;
import ca.qc.bb.p55.georges.TP1.IOnItemClickListener;
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

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                removeItem((long) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(mainActivityModel.getRecyclerView());

        mainActivityModel.getAdapter().setOnItemClickListener(new IOnItemClickListener() {
            @Override
            public void onItemClick(int position, String nom) {
                openActivityShowListItems(position, nom);
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

                String listName = data.getStringExtra("listName");
                String listItems = data.getStringExtra("listItems");
                if (listItems != null && !listItems.isEmpty()) {
                    mainActivityModel.updateListsItems(listName, listItems);
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

    private void openActivityShowListItems(int position, String nom) {
        Intent intent = new Intent(this, ActivityShowListItems.class);

        String listItems = mainActivityModel.getListItems(nom);

        intent.putExtra("listName", nom);
        intent.putExtra("listItems", listItems);

        startActivityForResult(intent, 1);
    }

    private void removeItem(long id) {
        mainActivityModel.removeItem(id);
    }

}



/*
    TODO:
          2. Split ActivityAddList to MVC style
          3. Check for returned strings not only null but also check if isEmpty() and trim() them.
          4. Make the mainActivity's view actually do something
          5. Add view to ActivityShowItems
          6. Remove auto-caps for "nom"
          7. Swipe to delete implementation
          8. save on sql thingy
          9. use stringbuilder to save items
          10. add hints on textViews
          11. find a way to know which activity ended in mainActivity as to not confucious both
          12. instead of identifying a list by its name, do it by its id
 */