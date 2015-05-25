package tads2.grupo.wishlist.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import tads2.grupo.wishlist.R;

/**
 * Created by PBalduino on 25/05/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "WISHLIST";
    private static final int DATABASE_VERSION = 1;

    public static final java.lang.String WISHES_TABLE_NAME = "WISHES";
    private static final java.lang.String WISHES_TABLE_CREATE = "CREATE TABLE " + WISHES_TABLE_NAME
            + "(ID INTEGER PRIMARY KEY ASC NOT NULL,"
            + " PRODUCT TEXT NOT NULL,"
            + " CATEGORY TEXT NOT NULL,"
            + " MINIMUM_VALUE REAL NOT NULL,"
            + " MAXIMUM_VALUE REAL NOT NULL,"
            + " STORES TEXT NOT NULL);";

    private static DatabaseHelper instance;

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHelper getInstance(Context context) {
        if(instance == null) {
            instance = new DatabaseHelper(context);
        }

        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(WISHES_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public SQLiteDatabase getDatabase() {
        return getWritableDatabase();
    }
}