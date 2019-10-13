package ca.qc.bb.p55.georges.TP1.ActivityShowItems;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ca.qc.bb.p55.georges.TP1.ActivityAddObject.ActivityAddObject;
import ca.qc.bb.p55.georges.TP1.MyItemsRecyclerView.MyItem;
import ca.qc.bb.p55.georges.TP1.MyItemsRecyclerView.MyItemAdapter;
import ca.qc.bb.p55.georges.TP1.MyListsRecyclerView.MyList;
import ca.qc.bb.p55.georges.client.R;

public class ActivityShowItems extends AppCompatActivity {

    private MyList myList;

    private ActivityShowItemsModel activityShowItemsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_myitems);

        // be in the view
        myList = (MyList) getIntent().getSerializableExtra("myList");
        ((TextView) findViewById(R.id.txtViewListName)).setText(myList.getNom());

        activityShowItemsModel = new ActivityShowItemsModel(this, myList);
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
                // TODO: SENDD MYLIST INFOO BACKKKK TO MAIN ACTIVITY OMGGG!!
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
        myList.getlistItems().add(new MyItem(content));

        activityShowItemsModel.getAdapter().notifyItemInserted(activityShowItemsModel.getAdapter().getItemCount() - 1);
        activityShowItemsModel.getAdapter().notifyDataSetChanged();
    }

}

