package ca.qc.bb.p55.georges.client;

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

public class Activity2 extends AppCompatActivity {

    public List<ImageView> imagesProfils;
    public EditText etNom;
    public EditText etPrenom;
    public ImageView ivProfilSelectionne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        etNom = findViewById(R.id.editTextNomFamille);
        etPrenom = findViewById(R.id.editTextPrenom);

        imagesProfils = new ArrayList<>();

        fillImagesProfilList();
        setOnClickEventProfilePics();

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
                intent.putExtra("idPhoto", (Integer) ivProfilSelectionne.getTag());

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

    private void fillImagesProfilList() {
        imagesProfils.add((ImageView) findViewById(R.id.imgViewCatfish));
        imagesProfils.add((ImageView) findViewById(R.id.imgViewGreta));
        imagesProfils.add((ImageView) findViewById(R.id.imgViewElon));
        imagesProfils.add((ImageView) findViewById(R.id.imgViewNeil));

        imagesProfils.get(0).setTag(R.drawable.catfish);
        imagesProfils.get(1).setTag(R.drawable.greta_thunberg);
        imagesProfils.get(2).setTag(R.drawable.elon_musk);
        imagesProfils.get(3).setTag(R.drawable.neil_degrass_tyson);
    }

    private void setOnClickEventProfilePics() {
        for (int i = 0; i < imagesProfils.size(); i++) {
            final ImageView ivTemp = imagesProfils.get(i);

            ivTemp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ivProfilSelectionne != null) {
                        ivProfilSelectionne.setColorFilter(Color.argb(128, 0, 0, 0));
                    }

                    ivTemp.setColorFilter(Color.argb(0, 255, 255, 255));
                    ivProfilSelectionne = ivTemp;
                }
            });
        }
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

        if (ivProfilSelectionne == null) {
            Toast.makeText(this, getResources().getString(R.string.askChooseImage),
                    Toast.LENGTH_SHORT).show();
            isEmpty = true;
        }

        return isEmpty;
    }

    private void presetValues() {
        String nom = getIntent().getStringExtra("nom");
        ((EditText) findViewById(R.id.editTextNomFamille)).setText(nom);

        String prenom = getIntent().getStringExtra("prenom");
        ((EditText) findViewById(R.id.editTextPrenom)).setText(prenom);

        int idPhoto = getIntent().getIntExtra("idPhoto", -1);
        for (ImageView image : imagesProfils) {
            if ((Integer) image.getTag() == idPhoto) {
                image.setColorFilter(Color.argb(0, 255, 255, 255));
                ivProfilSelectionne = image;
            }
        }
    }
}
