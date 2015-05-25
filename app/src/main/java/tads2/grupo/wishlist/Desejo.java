package tads2.grupo.wishlist;

/**
 * Created by PBalduino on 24/05/2015.
 */
public final class Desejo {
    private final String produto;
    private final String categoria;
    private final String lojas;
    private final double precoMinimo;
    private final double precoMaximo;

    public Desejo(final String produto,
                  final String categoria,
                  final String lojas,
                  final double precoMinimo,
                  final double precoMaximo) {
        this.produto = produto;
        this.categoria = categoria;
        this.lojas = lojas;
        this.precoMinimo = precoMinimo;
        this.precoMaximo = precoMaximo;
    }

    @Override
    public String toString() {
        return new StringBuilder().append(produto).append(" - ").append(categoria).toString();
    }
}