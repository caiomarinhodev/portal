package models;

/**
 * Created by X on 14/03/2015.
 */
public class Avaliacao {
    private Usuario usuario;
    private Tema tema;
    private int valor;

    public Avaliacao(Usuario usuario, Tema tema){
        this.usuario = usuario;
        this.tema = tema;
    }
    public Usuario getUsuario() {
        return usuario;
    }

    public Tema getTema() {
        return tema;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
