package tads2.grupo.wishlist;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import tads2.grupo.wishlist.data.DesejosDao;

public class EditarDesejoActivity extends AppCompatActivity {

    private TextView textProduto;
    private TextView textCategoria;
    private TextView textValorMinimo;
    private TextView textValorMaximo;
    private TextView textLojas;
    private TextView textId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_desejo);

        Bundle bundle = getIntent().getExtras();

        Desejo desejo = (Desejo) bundle.get("desejo");

        textProduto = (TextView) findViewById(R.id.textNomeProduto);
        textCategoria = (TextView) findViewById(R.id.textCategoria);
        textValorMinimo = (TextView) findViewById(R.id.textValorMinimo);
        textValorMaximo = (TextView) findViewById(R.id.textValorMaximo);
        textLojas = (TextView) findViewById(R.id.textLojas);
        textId = (TextView) findViewById(R.id.textId);

        textProduto.setText(desejo.getProduto());
        textCategoria.setText(desejo.getCategoria());
        textValorMinimo.setText(desejo.getValorMinimo().toString());
        textValorMaximo.setText(desejo.getValorMaximo().toString());
        textLojas.setText(desejo.getLojas());
        textId.setText(desejo.getId());
    }

    public void onButtonGravarDesejoClick(View view) {
        Desejo desejo = new Desejo(Integer.parseInt(textId.getText().toString()),
                    textProduto.getText().toString(),
                    textCategoria.getText().toString(),
                    textLojas.getText().toString(),
                    Double.parseDouble(textValorMinimo.getText().toString()),
                    Double.parseDouble(textValorMaximo.getText().toString()));

        new DesejosDao(this).save(desejo);

        new AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage("Desejo gravado")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setNegativeButton("OK", null)
                .create()
                .show();
    }
}