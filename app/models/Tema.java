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
    private Long idTema;
    @Column
    private String nome;

    @ManyToOne
    @JoinColumn(name="idDisciplina")
    private Disciplina disciplina;

    @OneToMany(mappedBy = "TEMA")
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
        return idTema;
    }

    public void setID(Long ID) {
        this.idTema = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
