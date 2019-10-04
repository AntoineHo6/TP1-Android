package ca.qc.bb.p55.georges.client.MainActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ca.qc.bb.p55.georges.client.Activity2.Activity2;
import ca.qc.bb.p55.georges.client.Client;
import ca.qc.bb.p55.georges.client.ClientAdapter;
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

        mainActivityModel.getAdapter().setOnItemClickListener(new ClientAdapter.OnItemClickListener() {
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
                String prenom = data.getStringExtra("prenom");
                int idPhoto = data.getIntExtra("idPhoto", -1);

                addClientToList(nom, prenom, idPhoto);
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
        Intent intent = new Intent(this, Activity2.class);
        startActivityForResult(intent, 1);
    }

    private void openActivity2Edit(int position){
        String nom = mainActivityModel.getList().get(position).getNom();
        String prenom = mainActivityModel.getList().get(position).getPrenom();
        int idPhoto = mainActivityModel.getList().get(position).getImage();
        mainActivityModel.getList().remove(position);

        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra("nom", nom);
        intent.putExtra("prenom", prenom);
        intent.putExtra("idPhoto", idPhoto);
        startActivityForResult(intent, 1);
    }

    private void addClientToList(String nom, String prenom, int idPhoto) {
        mainActivityModel.getList().add(new Client(nom, prenom, idPhoto));

        // Sort la liste
        Collections.sort(mainActivityModel.getList(), new Comparator<Client>() {
            @Override
            public int compare(Client o1, Client o2) {
                return o1.getNom().compareTo(o2.getNom());
            }
        });

        mainActivityModel.getAdapter().notifyItemInserted(mainActivityModel.getAdapter().getItemCount() - 1);
        mainActivityModel.getAdapter().notifyDataSetChanged();
    }
}
