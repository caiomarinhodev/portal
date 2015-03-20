package models;

import javax.persistence.*;

/**
 * Created by X on 20/03/2015.
 */
@Entity
@Table(name = "DENUNCIA")
public class Denuncia {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long denunciaID;

    @Column
    private long usuarioID;

    @Column
    private long dicaID;

    public Denuncia(long user, long dica){
        usuarioID = user;
        dicaID = dica;
    }

    public long getDenunciaID() {
        return denunciaID;
    }

    public long getUsuarioID() {
        return usuarioID;
    }

    public long getDicaID() {
        return dicaID;
    }
}
