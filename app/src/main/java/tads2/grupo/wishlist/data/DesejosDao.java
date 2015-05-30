package tads2.grupo.wishlist.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.SimpleCursorAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

import tads2.grupo.wishlist.Desejo;

/**
 * Created by PBalduino on 25/05/2015.
 */
public class DesejosDao {
    private final Context context;

    public DesejosDao(final Context context) {
        this.context = context;
    }

    public Cursor getCursor() {
        SQLiteDatabase database = DatabaseHelper.getInstance(context).getDatabase();

        return database.rawQuery("SELECT ID _id, PRODUCT, CATEGORY, MINIMUM_VALUE, MAXIMUM_VALUE, STORES FROM WISHES;", null);
    }

    public void save(Desejo desejo) {
        SQLiteDatabase database = DatabaseHelper.getInstance(context).getDatabase();

        ContentValues values = new ContentValues();

        values.put("PRODUCT", desejo.getProduto());
        values.put("CATEGORY", desejo.getCategoria());
        values.put("MINIMUM_VALUE", desejo.getValorMinimo());
        values.put("MAXIMUM_VALUE", desejo.getValorMaximo());
        values.put("STORES", desejo.getLojas());

        if(desejo.getId() == 0) {
            Log.d("DesejosDao", "Wish inserted - " + values);
            database.insert(DatabaseHelper.WISHES_TABLE_NAME, null, values);
        } else {
            Log.d("DesejosDao", "Wish " + desejo.getId() + " (" + desejo.getProduto() + ") updated - " + values);
            database.update(DatabaseHelper.WISHES_TABLE_NAME, values, "ID = ?", new String[] {"" + desejo.getId()});
        }
    }

    public void delete(Desejo desejo) {
        SQLiteDatabase database = DatabaseHelper.getInstance(context).getDatabase();

        database.delete(DatabaseHelper.WISHES_TABLE_NAME, "ID = ?", new String[] {"" + desejo.getId()});
        Log.d("DesejosDao", "Wish " + desejo.getId() + " was deleted");
    }
}