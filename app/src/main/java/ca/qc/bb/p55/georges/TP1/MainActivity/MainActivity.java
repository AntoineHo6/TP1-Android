package ca.qc.bb.p55.georges.TP1.MainActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import ca.qc.bb.p55.georges.TP1.ActivityAddObject.ActivityAddObject;
import ca.qc.bb.p55.georges.TP1.ActivityShowItems.ActivityShowItems;
import ca.qc.bb.p55.georges.TP1.IOnItemClickListener;
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

            @Override
            public void onEditClick(int position) {
                openActivityAddListEdit(position);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String nom = data.getStringExtra("nom");

                addList(nom);
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

    private void openActivityAddListEdit(int position){
        String nom = mainActivityModel.getList().get(position).getNom();
        mainActivityModel.getList().remove(position);

        Intent intent = new Intent(this, ActivityAddObject.class);
        intent.putExtra("nom", nom);
        intent.putExtra("objectType", "list");
        startActivityForResult(intent, 1);
    }

    private void addList(String nom) {
        mainActivityModel.getList().add(new MyList(nom));

        mainActivityModel.sortMyLists();

        mainActivityModel.getAdapter().notifyItemInserted(mainActivityModel.getAdapter().getItemCount() - 1);
        mainActivityModel.getAdapter().notifyDataSetChanged();
    }

    private void openActivityShowListItems(int position) {
        Intent intent = new Intent(this, ActivityShowItems.class);

        MyList myList = mainActivityModel.getList().get(position);
        intent.putExtra("myList", myList);
        startActivity(intent);
    }
}
