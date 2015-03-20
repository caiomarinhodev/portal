package models;

import javax.persistence.*;

/**
 * Created by X on 14/03/2015.
 */
@Entity
@Table(name = "VOTO")
public class Voto {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long votoId;

    @Column
    private String usuario;

    @Column
    private long idDica;

    @Column
    private int voto;

    public Voto (Usuario usuario, Dica dica, int voto){
        this.usuario = usuario.getEmail();
        this.idDica = dica.getDicaID();
        this.voto = voto;
    }

    public Voto(){
        
    }

    public void setVoto(int voto) {
        this.voto = voto;
    }

    public String getUsuario() {
        return usuario;
    }

    public long getVotoId() {
        return votoId;
    }

    public long getIdDica() {
        return idDica;
    }

    public int getVoto() {
        return voto;
    }
}
