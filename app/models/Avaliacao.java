package models;

import javax.persistence.*;

/**
 * Created by X on 14/03/2015.
 */
@Entity
@Table(name = "AVALIACAO")
public class Avaliacao {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long avaliacaoID;

    @Column
    private long usuario;

    @Column
    private long tema;

    @Column
    private int valor;


    public Avaliacao(Usuario usuario, Tema tema){
        this.usuario = usuario.getId();
        this.tema = tema.getID();
    }

    public long getAvaliacaoID() {
        return avaliacaoID;
    }
    public long getUsuario() {
        return usuario;
    }

    public long getTema() {
        return tema;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
