package models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by caio on 14/03/15.
 */
@Entity
@Table(name = "DISCIPLINA")
public class Disciplina {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idDisciplina;
    @Column
    private String nome;

    @OneToMany
    private List<Tema> temas;

    public Disciplina(String nome, List<Tema> listaDeTemas) {
        this.nome = nome;
        this.temas = listaDeTemas;
    }

    public Disciplina() {

    }

    public long getIdDisciplina() {
        return idDisciplina;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Tema> getTemas() {
        return temas;
    }
}