package ca.qc.bb.p55.georges.client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class MainActivity extends AppCompatActivity {
    private ArrayList<Client>           list;
    private RecyclerView                recyclerView;
    private ClientAdapter               adapter;
    private RecyclerView.LayoutManager  layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        list.add(new Client("Côté", "Georges", R.drawable.tyrion));
        list.add(new Client("Legault", "François", R.drawable.shrek));

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new ClientAdapter(list);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new ClientAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                showConfirmationDialog(position);
            }

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


    public void showConfirmationDialog(int position) {
        final int fPosition = position;
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(getResources().getString(R.string.confirmation));
        alertDialog.setMessage(getResources().getString(R.string.askIfSure));
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getResources().getString(R.string.yes),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        removeItem(fPosition);
                    }
                }
        );
        alertDialog.setButton
                (AlertDialog.BUTTON_NEGATIVE, getResources().getString(R.string.no),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface arg0, int arg1) {
                            }
                        }
                );
        alertDialog.show();
    }

    private void removeItem(int position) {
        list.remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, adapter.getItemCount());
    }

    private void openActivity2() {
        Intent intent = new Intent(this, Activity2.class);
        startActivityForResult(intent, 1);
    }

    private void openActivity2Edit(int position){
        String nom = list.get(position).getNom();
        String prenom = list.get(position).getPrenom();
        int idPhoto = list.get(position).getImage();
        list.remove(position);

        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra("nom", nom);
        intent.putExtra("prenom", prenom);
        intent.putExtra("idPhoto", idPhoto);
        startActivityForResult(intent, 1);
    }

    private void addClientToList(String nom, String prenom, int idPhoto) {
        list.add(new Client(nom, prenom, idPhoto));

        // Sort la liste
        Collections.sort(list, new Comparator<Client>() {
            @Override
            public int compare(Client o1, Client o2) {
                return o1.getNom().compareTo(o2.getNom());
            }
        });

        adapter.notifyItemInserted(adapter.getItemCount() - 1);
        adapter.notifyDataSetChanged();
    }
}
