package tads2.grupo.wishlist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import tads2.grupo.wishlist.data.DesejosDao;

public class InserirDesejoActivity extends AppCompatActivity implements DialogInterface.OnClickListener, DialogInterface.OnDismissListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_desejo);
    }

    public void onButtonIncluirDesejoClick(View view) {
        TextView textProduto = (TextView) findViewById(R.id.textNomeProduto);
        TextView textCategoria = (TextView) findViewById(R.id.textCategoria);
        TextView textLojas = (TextView) findViewById(R.id.textLojas);
        TextView textValorMinimo = (TextView) findViewById(R.id.textValorMinimo);
        TextView textValorMaximo = (TextView) findViewById(R.id.textValorMaximo);

        Desejo desejo = new Desejo(0,
                textProduto.getText().toString(),
                textCategoria.getText().toString(),
                textLojas.getText().toString(),
                toDouble(textValorMinimo.getText()),
                toDouble(textValorMaximo.getText()));

        new DesejosDao(this).save(desejo);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage("Desejo gravado")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setNegativeButton("OK", this)
                .setOnDismissListener(this)
                .create();

        dialog.show();
    }

    private double toDouble(CharSequence text) {
        return Double.parseDouble(text.toString());
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