package ca.qc.bb.p55.georges.TP1.ActivityAddObject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import ca.qc.bb.p55.georges.client.R;

public class ActivityAddObject extends AppCompatActivity {

    public EditText etNom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);

        etNom = findViewById(R.id.editTextListName);

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

                String nom = ((EditText) findViewById(R.id.editTextListName)).getText().toString();
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
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            default:
                return super.onOptionsItemSelected(item);

        }
    }


    private boolean checkForEmpty() {
        boolean isEmpty = false;

        if (etNom.getText().toString().trim().isEmpty()) {
            etNom.setError(getResources().getString(R.string.askEnterSomething));
            isEmpty = true;
        }
        else {
            etNom.setError(null); // Enlève l’erreur du champ de saisie
        }

        return isEmpty;
    }

    private void presetValues() {
        String nom = getIntent().getStringExtra("nom");
        ((EditText) findViewById(R.id.editTextListName)).setText(nom);

        // modifie add object text
        String type = getIntent().getStringExtra("objectType");
        if (type.equals("list")) {
            ((TextView) findViewById(R.id.txtViewListName)).setText(R.string.newList);
        }
        else {
            ((TextView) findViewById(R.id.txtViewListName)).setText(R.string.newItem);
        }
    }
}
