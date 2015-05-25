package tads2.grupo.wishlist;

import java.io.Serializable;

/**
 * Created by PBalduino on 24/05/2015.
 */
public final class Desejo implements Serializable {
    private final String produto;
    private final String categoria;
    private final String lojas;
    private final double valorMinimo;
    private final double valorMaximo;

    public Desejo(final String produto,
                  final String categoria,
                  final String lojas,
                  final double valorMinimo,
                  final double valorMaximo) {
        this.produto = produto;
        this.categoria = categoria;
        this.lojas = lojas;
        this.valorMinimo = valorMinimo;
        this.valorMaximo = valorMaximo;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(produto).append(" - ").append(categoria).toString();
    }

    public String getProduto() {
        return produto;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getValorMinimo() {
        return valorMinimo;
    }

    public double getValorMaximo() {
        return valorMaximo;
    }

    public String getLojas() {
        return lojas;
    }
}