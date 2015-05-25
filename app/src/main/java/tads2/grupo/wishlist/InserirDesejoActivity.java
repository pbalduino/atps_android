package tads2.grupo.wishlist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class InserirDesejoActivity extends AppCompatActivity implements DialogInterface.OnClickListener, DialogInterface.OnDismissListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_desejo);

        loadData();
    }

    public void onButtonIncluirDesejoClick(View view) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage("Desejo gravado")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setNegativeButton("OK", this)
                .setOnDismissListener(this)
                .create();

        dialog.show();
    }

    private void loadData() {

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Intent intent = new Intent(this, ListarDesejosActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        Intent intent = new Intent(this, ListarDesejosActivity.class);
        startActivity(intent);
    }
}