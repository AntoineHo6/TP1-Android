package ca.qc.bb.p55.georges.TP1.ActivityShowListItems;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import ca.qc.bb.p55.georges.TP1.ActivityAddObject.ActivityAddObject;
import ca.qc.bb.p55.georges.client.R;

public class ActivityShowListItems extends AppCompatActivity {

    // TODO: move this into model?
//    private MyList myList;

    private ActivityShowListItemsModel activityShowListItemsModel;
    private ActivityShowListItemsView activityShowListItemsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_myitems);

        // be in the view
        String listName = getIntent().getStringExtra("listName");
        ((TextView) findViewById(R.id.txtViewListName)).setText(listName);

        String items = getIntent().getStringExtra("listItems");

        activityShowListItemsModel = new ActivityShowListItemsModel(this, listName, items);
        activityShowListItemsView = new ActivityShowListItemsView(this);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                removeItem(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(activityShowListItemsModel.getRecyclerView());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (activityShowListItemsModel.getAdapter().getList().isEmpty()) {
                    finish();
                }
                else {
                    Intent intent = new Intent();
                    activityShowListItemsModel.updateTokenizedItems();
                    String tokenizedItems = activityShowListItemsModel.getTokenizedItems();
                    intent.putExtra("listName", activityShowListItemsModel.getListName());
                    intent.putExtra("listItems", tokenizedItems);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;
            case R.id.addMenu:
                openActivityAddItem();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String nom = data.getStringExtra("nom");
                addItemToList(nom);
            }
        }
    }

    private void openActivityAddItem() {
        Intent intent = new Intent(this, ActivityAddObject.class);
        intent.putExtra("objectType", "item");
        startActivityForResult(intent, 1);
    }

    private void addItemToList(String content) {
        activityShowListItemsModel.addItemToAdapter(content);
        activityShowListItemsView.updateItemsInterface(activityShowListItemsModel.getAdapter());
    }

    private void removeItem(int position) {
        activityShowListItemsModel.removeItem(position);
    }


}

