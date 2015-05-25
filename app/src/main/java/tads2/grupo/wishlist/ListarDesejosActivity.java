package tads2.grupo.wishlist;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListarDesejosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listar_desejos);

        List<Desejo> desejos = new ArrayList<Desejo>();

        desejos.add(new Desejo("Baixo", "Instrumento musical", "Made in Brazil", 1000.0, 3000.0));
        desejos.add(new Desejo("Guitarra", "Instrumento musical", "UFO Som", 500.0, 1500.0));

        ArrayAdapter<Desejo> adapter = new ArrayAdapter<Desejo>(this, android.R.layout.simple_list_item_1, desejos);

        ListView listView = (ListView) findViewById(R.id.listViewDesejos);

        Log.w("ListView", "" + adapter);
        Log.w("ListView", "" + listView);

        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listar_desejos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id)
        {
            case R.id.action_new: {
                AlertDialog dialog = new AlertDialog
                        .Builder(this)
                        .setMessage("Superstyling")
                        .create();

                dialog.show();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}