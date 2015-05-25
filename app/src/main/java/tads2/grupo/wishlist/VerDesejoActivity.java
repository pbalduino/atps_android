package tads2.grupo.wishlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class VerDesejoActivity extends AppCompatActivity {

    private Desejo desejo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_desejo);

        Bundle bundle = getIntent().getExtras();

        desejo = (Desejo) bundle.get("desejo");

        TextView txtProduto = (TextView) findViewById(R.id.textNomeProduto);
        TextView txtCategoria = (TextView) findViewById(R.id.textCategoria);
        TextView txtValorMinimo = (TextView) findViewById(R.id.textValorMinimo);
        TextView txtValorMaximo = (TextView) findViewById(R.id.textValorMaximo);
        TextView txtLojas = (TextView) findViewById(R.id.textLojas);

        txtProduto.setText(desejo.getProduto());
        txtCategoria.setText(desejo.getCategoria());
        txtValorMinimo.setText(desejo.getValorMinimo().toString());
        txtValorMaximo.setText(desejo.getValorMaximo().toString());
        txtLojas.setText(desejo.getLojas());
    }

    public void onButtonEditarClick(View view) {
        Intent intent = new Intent(this, EditarDesejoActivity.class);
        intent.putExtra("desejo", desejo);

        startActivity(intent);
    }
}
