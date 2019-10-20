package ca.qc.bb.p55.georges.TP1.ActivityAddObject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import ca.qc.bb.p55.georges.client.R;

public class ActivityAddObject extends AppCompatActivity {

    private ActivityAddObjectModel activityAddObjectModel;
    private ActivityAddObjectView activityAddObjectView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);

        activityAddObjectModel = new ActivityAddObjectModel(this);
        activityAddObjectView = new ActivityAddObjectView(this);

        EditText etNom = findViewById(R.id.editTextListName);
        activityAddObjectModel.setEtNom(etNom);

        if (getIntent().getExtras() != null) {
            String nom = getIntent().getStringExtra("nom");
            String type = getIntent().getStringExtra("objectType");
            activityAddObjectView.presetValues(nom, type);
        }

        // SET event bouton soumettre
        findViewById(R.id.btnSoumettre).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isEmpty = activityAddObjectModel.checkForEmpty();
                if (isEmpty) {
                    return;
                }

                Intent intent = new Intent();
                String nom = ((EditText) findViewById(R.id.editTextListName)).getText().toString();
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




}
