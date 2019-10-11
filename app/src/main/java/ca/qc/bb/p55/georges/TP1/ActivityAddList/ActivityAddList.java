package ca.qc.bb.p55.georges.TP1.ActivityAddList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import ca.qc.bb.p55.georges.client.R;

public class ActivityAddList extends AppCompatActivity {

    public EditText etNom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        etNom = findViewById(R.id.editTextNomFamille);

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

                intent.putExtra("nom", nom);

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

        return isEmpty;
    }

    private void presetValues() {
        String nom = getIntent().getStringExtra("nom");
        ((EditText) findViewById(R.id.editTextNomFamille)).setText(nom);
    }
}
