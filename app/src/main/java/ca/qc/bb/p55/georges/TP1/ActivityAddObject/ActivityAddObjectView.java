package ca.qc.bb.p55.georges.TP1.ActivityAddObject;

import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ca.qc.bb.p55.georges.client.R;

public class ActivityAddObjectView {

    private AppCompatActivity activityAddObject;

    public ActivityAddObjectView(AppCompatActivity activityAddObject) {
        this.activityAddObject = activityAddObject;
    }

    public void presetValues(String nom, String type) {
        ((EditText) activityAddObject.findViewById(R.id.editTextListName)).setText(nom);

        // modifie add object text
        if (type.equals("list")) {
            ((TextView) activityAddObject.findViewById(R.id.txtViewListName)).setText(R.string.newList);
        }
        else {
            ((TextView) activityAddObject.findViewById(R.id.txtViewListName)).setText(R.string.newItem);
        }
    }
}
