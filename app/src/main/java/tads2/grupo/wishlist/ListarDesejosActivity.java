package tads2.grupo.wishlist;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

import tads2.grupo.wishlist.data.DesejosDao;

public class ListarDesejosActivity extends AppCompatActivity {

    private final ListarDesejosActivity self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listar_desejos);

        loadData();

        ListView listView = (ListView)findViewById(R.id.listViewDesejos);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) parent.getItemAtPosition(position);

                Desejo desejo = new Desejo(cursor.getInt(0),
                                           cursor.getString(1),
                                           cursor.getString(2),
                                           cursor.getString(5),
                                           cursor.getDouble(3),
                                           cursor.getDouble(4));

                Intent intent = new Intent(self, VerDesejoActivity.class);
                intent.putExtra("desejo", desejo);

                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Desejo desejo = (Desejo)parent.getItemAtPosition(position);

                Intent intent = new Intent(self, EditarDesejoActivity.class);
                intent.putExtra("desejo", desejo);

                startActivity(intent);

                return true;
            }
        });

    }

    private void loadData() {

        Cursor cursor = new DesejosDao(this).getCursor();
        startManagingCursor(cursor);

        String[] columns = new String[] { "PRODUCT" };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_expandable_list_item_1, cursor, columns, new int[]{android.R.id.text1});

        ListView listView = (ListView) findViewById(R.id.listViewDesejos);

        listView.setAdapter(adapter);
    }

    public void onButtonIncluirDesejoClick(View view) {
        gotoIncluirDesejo();
    }

    private void gotoIncluirDesejo() {
        Intent incluirDesejo = new Intent(this, InserirDesejoActivity.class);
        startActivity(incluirDesejo);
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
        switch (id) {
            case R.id.action_new: {
                gotoIncluirDesejo();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}