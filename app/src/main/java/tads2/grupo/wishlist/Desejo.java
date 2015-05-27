package tads2.grupo.wishlist;

import android.database.Cursor;

import java.io.Serializable;

/**
 * Created by PBalduino on 24/05/2015.
 */
public final class Desejo implements Serializable {
    private final int id;
    private final String produto;
    private final String categoria;
    private final String lojas;
    private final double valorMinimo;
    private final double valorMaximo;

    public Desejo(final int id,
                  final String produto,
                  final String categoria,
                  final String lojas,
                  final double valorMinimo,
                  final double valorMaximo) {
        this.id = id;
        this.produto = produto;
        this.categoria = categoria;
        this.lojas = lojas;
        this.valorMinimo = valorMinimo;
        this.valorMaximo = valorMaximo;
    }

    public int getId() { return id; }
    
    public String getProduto() {
        return produto;
    }

    public String getCategoria() {
        return categoria;
    }

    public Double getValorMinimo() {
        return valorMinimo;
    }

    public Double getValorMaximo() {
        return valorMaximo;
    }

    public String getLojas() {
        return lojas;
    }

    public static Desejo fromCursor(Cursor cursor) {
        return new Desejo(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(5),
                cursor.getDouble(3),
                cursor.getDouble(4));
    }
}