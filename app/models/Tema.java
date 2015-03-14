package models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by X on 14/03/2015.
 */
@Entity
@Table(name = "TEMA")
public class Tema {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @Column
    private String nome;

    private List<Dica> dicas;

    public Tema(String nome){
        this.nome = nome;
    }

    public List<Dica> getDicas() {
        return dicas;
    }

    public void setDicas(List<Dica> dicas) {
        this.dicas = dicas;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
