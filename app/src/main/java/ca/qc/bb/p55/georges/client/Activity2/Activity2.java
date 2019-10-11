package ca.qc.bb.p55.georges.client.Activity2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ca.qc.bb.p55.georges.client.R;

public class Activity2 extends AppCompatActivity {

    public EditText etNom;
    public EditText etPrenom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        etNom = findViewById(R.id.editTextNomFamille);
        etPrenom = findViewById(R.id.editTextPrenom);

        if (getIntent().getExtras() != null) {
            presetValues();
        }

        // SET event bouton soumettre
        findViewById(R.id.btnSoumettre).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isEmpty = checkForEmpty();
                if (isEmpty) {
                    return;
                }

                Intent intent = new Intent();

                String nom = ((EditText) findViewById(R.id.editTextNomFamille)).getText().toString();
                // Uppercase first letter
                nom = nom.substring(0, 1).toUpperCase() + nom.substring(1);

                String prenom = ((EditText) findViewById(R.id.editTextPrenom)).getText().toString();
                // Uppercase first letter
                prenom = prenom.substring(0, 1).toUpperCase() + prenom.substring(1);

                intent.putExtra("nom", nom);
                intent.putExtra("prenom", prenom);

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.undo_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.undoMenu) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    private boolean checkForEmpty() {
        boolean isEmpty = false;

        if (etNom.getText().toString().trim().isEmpty()) {
            etNom.setError(getResources().getString(R.string.askEnterFirstName));
            isEmpty = true;
        }
        else {
            etNom.setError(null); // Enlève l’erreur du champ de saisie
        }

        if (etPrenom.getText().toString().trim().isEmpty()) {
            etPrenom.setError(getResources().getString(R.string.askEnterLastName));
            isEmpty = true;
        }
        else {
            etPrenom.setError(null); // Enlève l’erreur du champ de saisie
        }

        return isEmpty;
    }

    private void presetValues() {
        String nom = getIntent().getStringExtra("nom");
        ((EditText) findViewById(R.id.editTextNomFamille)).setText(nom);

        String prenom = getIntent().getStringExtra("prenom");
        ((EditText) findViewById(R.id.editTextPrenom)).setText(prenom);
    }
}
