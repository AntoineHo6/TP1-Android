package ca.qc.bb.p55.georges.TP1.ActivityAddObject;

import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import ca.qc.bb.p55.georges.client.R;

public class ActivityAddObjectView {

    private AppCompatActivity activityAddObject;

    public ActivityAddObjectView(AppCompatActivity activityAddObject) {
        this.activityAddObject = activityAddObject;
    }

    public void presetValues(String nom, String type) {
        ((EditText) activityAddObject.findViewById(R.id.editTextListName)).setText(nom);

        if (type.equals("list")) {
            ((EditText) activityAddObject.findViewById(R.id.editTextListName)).setHint(R.string.YourNewListsName);
        }
        else {
            ((EditText) activityAddObject.findViewById(R.id.editTextListName)).setHint(R.string.YourNewItemsContent);
        }
    }
}
