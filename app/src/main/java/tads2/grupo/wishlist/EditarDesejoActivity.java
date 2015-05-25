package tads2.grupo.wishlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.NumberFormat;

public class EditarDesejoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_desejo);

        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        Bundle bundle = getIntent().getExtras();

        Desejo desejo = (Desejo) bundle.get("desejo");

        TextView txtProduto = (TextView) findViewById(R.id.textNomeProduto);
        TextView txtCategoria = (TextView) findViewById(R.id.textCategoria);
        TextView txtValorMinimo = (TextView) findViewById(R.id.textValorMinimo);
        TextView txtValorMaximo = (TextView) findViewById(R.id.textValorMaximo);
        TextView txtLojas = (TextView) findViewById(R.id.textLojas);

        txtProduto.setText(desejo.getProduto());
        txtCategoria.setText(desejo.getCategoria());
        txtValorMinimo.setText(formatter.format(desejo.getValorMinimo()));
        txtValorMaximo.setText(formatter.format(desejo.getValorMaximo()));
        txtLojas.setText(desejo.getLojas());
    }
}