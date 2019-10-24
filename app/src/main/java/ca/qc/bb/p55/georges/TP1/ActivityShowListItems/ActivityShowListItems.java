package ca.qc.bb.p55.georges.TP1.ActivityShowListItems;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
        String items = getIntent().getStringExtra("listItems");

        activityShowListItemsModel = new ActivityShowListItemsModel(this, listName, items);
        activityShowListItemsView = new ActivityShowListItemsView(this);

        activityShowListItemsView.changeListName(listName);

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

        FloatingActionButton fab = findViewById(R.id.fabEmail);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_SUBJECT, activityShowListItemsModel.getListName());
                intent.putExtra(Intent.EXTRA_TEXT, activityShowListItemsModel.getFormattedItemsForEmail());
                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent, "Choose an email client"));
            }
        });
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
                Intent intent = new Intent();
                intent.putExtra("activityType", "ActivityShowListItems");
                intent.putExtra("listName", activityShowListItemsModel.getListName());
                if (activityShowListItemsModel.getAdapter().getList().isEmpty()) {
                    intent.putExtra("listItems", "");
                }
                else {
                    activityShowListItemsModel.updateTokenizedItems();
                    String tokenizedItems = activityShowListItemsModel.getTokenizedItems();
                    intent.putExtra("listItems", tokenizedItems);
                }
                setResult(RESULT_OK, intent);
                finish();
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

