package ca.qc.bb.p55.georges.TP1.MainActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import java.util.Collections;
import java.util.Comparator;

import ca.qc.bb.p55.georges.TP1.ActivityAddList.ActivityAddList;
import ca.qc.bb.p55.georges.TP1.MyList;
import ca.qc.bb.p55.georges.TP1.MyListAdapter;
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

        mainActivityModel.getAdapter().setOnItemClickListener(new MyListAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(int position) {
                openActivity2Edit(position);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String nom = data.getStringExtra("nom");

                addClientToList(nom);
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
            openActivity2();
        }

        return super.onOptionsItemSelected(item);
    }



    private void openActivity2() {
        Intent intent = new Intent(this, ActivityAddList.class);
        startActivityForResult(intent, 1);
    }

    private void openActivity2Edit(int position){
        String nom = mainActivityModel.getList().get(position).getNom();
        mainActivityModel.getList().remove(position);

        Intent intent = new Intent(this, ActivityAddList.class);
        intent.putExtra("nom", nom);
        startActivityForResult(intent, 1);
    }

    private void addClientToList(String nom) {
        mainActivityModel.getList().add(new MyList(nom));

        // Sort la liste
        Collections.sort(mainActivityModel.getList(), new Comparator<MyList>() {
            @Override
            public int compare(MyList o1, MyList o2) {
                return o1.getNom().compareTo(o2.getNom());
            }
        });

        mainActivityModel.getAdapter().notifyItemInserted(mainActivityModel.getAdapter().getItemCount() - 1);
        mainActivityModel.getAdapter().notifyDataSetChanged();
    }
}
