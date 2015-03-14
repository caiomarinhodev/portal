package models;

/**
 * Created by X on 14/03/2015.
 */
public class Voto {
    private Usuario usuario;
    private Dica dica;
    private int voto;

    public Voto (Usuario usuario, Dica dica, int voto){
        this.usuario = usuario;
        this.dica = dica;
        this.voto = voto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Dica getDica() {
        return dica;
    }

    public int getVoto() {
        return voto;
    }
}
