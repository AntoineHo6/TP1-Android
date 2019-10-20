package ca.qc.bb.p55.georges.TP1.ActivityAddObject;

import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ca.qc.bb.p55.georges.client.R;

public class ActivityAddObjectModel {

    public EditText etNom;

    private AppCompatActivity activityAddObject;

    public ActivityAddObjectModel(AppCompatActivity activityAddObject) {
        this.activityAddObject = activityAddObject;
    }

    public void setEtNom(EditText etNom) {
        this.etNom = etNom;
    }

    public boolean checkForEmpty() {
        boolean isEmpty = false;

        if (etNom.getText().toString().trim().isEmpty()) {
            etNom.setError(activityAddObject.getResources().getString(R.string.askEnterSomething));
            isEmpty = true;
        }
        else {
            etNom.setError(null); // Enlève l’erreur du champ de saisie
        }

        return isEmpty;
    }
}
