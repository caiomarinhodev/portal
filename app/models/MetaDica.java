package models;
import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by X on 14/03/2015.
 */
@Entity
@Table(name = "METADICA")
public class MetaDica {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long metaDicaID;

    @Column
    private long autor;

    @Column
    private Date data;

    @Column
    private long disciplina;

    @OneToMany
    List<Dica> dicas = new ArrayList<Dica>();

    @Column
    private int votos;

    public long getDisciplina() {
        return disciplina;
    }

    public List<Dica> getDicas() {
        return dicas;
    }

    public MetaDica (Usuario autor, Disciplina disciplina){
        this.autor = autor.getId();
        this.disciplina = disciplina.getIdDisciplina();
    }

    public long getMetaDicaID() {
        return metaDicaID;
    }

    public long getAutor() {
        return autor;
    }

    public Date getData() {
        return data;
    }

    public int getVotos() {
        return votos;
    }

    public void addDica(Dica dica){
        dicas.add(dica);
    }
}
