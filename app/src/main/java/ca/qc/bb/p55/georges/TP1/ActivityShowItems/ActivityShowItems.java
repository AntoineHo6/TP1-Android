package ca.qc.bb.p55.georges.TP1.ActivityShowItems;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ca.qc.bb.p55.georges.TP1.MyListsRecyclerView.MyList;
import ca.qc.bb.p55.georges.client.R;

public class ActivityShowItems extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_myitems);

        MyList myList = (MyList) getIntent().getSerializableExtra("myList");



        ((TextView) findViewById(R.id.txtViewListName)).setText(myList.getNom());

    }

}
