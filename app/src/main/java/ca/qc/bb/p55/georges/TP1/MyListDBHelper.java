package ca.qc.bb.p55.georges.TP1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import ca.qc.bb.p55.georges.TP1.MyListContract.*;
import ca.qc.bb.p55.georges.TP1.MyListsRecyclerView.MyList;

import androidx.annotation.Nullable;

public class MyListDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyLists.db";
    public static final int DATABASE_VERSION = 1;

    public MyListDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MYLISTS_TABLE = "CREATE TABLE " +
                MyListEntry.TABLE_NAME + " (" +
                MyListEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MyListEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                MyListEntry.COLUMN_ITEMS + " TEXT" +
                ");";

        db.execSQL(SQL_CREATE_MYLISTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MyListEntry.TABLE_NAME);
        onCreate(db);
    }
}
