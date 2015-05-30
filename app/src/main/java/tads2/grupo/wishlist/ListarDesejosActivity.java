package tads2.grupo.wishlist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ShareActionProvider;
import android.widget.SimpleCursorAdapter;

import tads2.grupo.wishlist.data.DesejosDao;

public class ListarDesejosActivity extends AppCompatActivity {

    private final ListarDesejosActivity self = this;
    private ListAdapter adapter;
    private ShareActionProvider shareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listar_desejos);

        loadData();

        ListView listView = (ListView) findViewById(R.id.listViewDesejos);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) parent.getItemAtPosition(position);

                Intent intent = new Intent(self, VerDesejoActivity.class);
                intent.putExtra("desejo", Desejo.fromCursor(cursor));

                startActivity(intent);
            }
        });

        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);

        getMenuInflater().inflate(R.menu.contextmenu_listar_desejos, menu);

        MenuItem item = menu.findItem(R.id.action_share);

        shareActionProvider = (ShareActionProvider) item.getActionProvider();

        item

        shareActionProvider.setOnShareTargetSelectedListener(new ShareActionProvider.OnShareTargetSelectedListener() {
            @Override
            public boolean onShareTargetSelected(ShareActionProvider source, Intent intent) {
                Log.d("ListarDesejosActivity", "onShareTargetSelected - " + source + " - " + intent);
                return true;
            }
        });

        Log.d("ListarDesejosActivity", "onCreateContextMenu: " + shareActionProvider);
    }

    @Override
    public boolean onContextItemSelected(MenuItem menuItem) {
        super.onContextItemSelected(menuItem);

        AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo)menuItem.getMenuInfo();

        final Desejo desejo = Desejo.fromCursor((Cursor) adapter.getItem(acmi.position));

        switch(menuItem.getItemId()) {
            case R.id.action_edit: {
                Intent intent = new Intent(self, EditarDesejoActivity.class);
                intent.putExtra("desejo", desejo);

                startActivity(intent);

                break;
            }
            case R.id.action_delete: {
                new AlertDialog.Builder(this)
                        .setTitle(R.string.app_name)
                        .setMessage("Deseja excluir este desejo?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DesejosDao dao = new DesejosDao(self);
                                dao.delete(desejo);
                            }
                        })
                        .setNegativeButton("Não", null)
                        .create()
                        .show();
                break;
            }
            case R.id.action_share: {
                break;
            }
        }

        return true;
    }

    private void loadData() {
        Cursor cursor = new DesejosDao(this).getCursor();
        startManagingCursor(cursor);

        String[] columns = new String[]{"PRODUCT"};

        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_expandable_list_item_1, cursor, columns, new int[]{android.R.id.text1});

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
        getMenuInflater().inflate(R.menu.menu_listar_desejos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_new: {
                gotoIncluirDesejo();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public void doShare(Intent shareIntent) {
        shareActionProvider.setShareIntent(shareIntent);
    }
}