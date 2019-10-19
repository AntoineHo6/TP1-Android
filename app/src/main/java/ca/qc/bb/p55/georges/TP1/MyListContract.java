package ca.qc.bb.p55.georges.TP1;

import android.provider.BaseColumns;

public class MyListContract {

    private MyListContract() {

    }

    public static final class MyListEntry implements BaseColumns {
        public static final String TABLE_NAME = "MyLists";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_ITEMS = "items";
    }
}
