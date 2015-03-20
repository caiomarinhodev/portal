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

    @Column
    private int votosPositivos;

    @Column
    private boolean aberto;

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

    public MetaDica() {

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

    public int getVotosPositivos() {
        return votosPositivos;
    }

    public boolean isAberto() {
        return aberto;
    }

    public void incrementaVotos(Voto voto) {
        if (isAberto()) {
            if (voto.getVoto() == 1) {
                votosPositivos++;
                if (getVotosPositivos() == 20){
                    aberto = false;
                }
            }
            votos++;
        }
    }

    public void decrementaVotos(Voto voto) {
        if (voto.getVoto() == 1) {
            votosPositivos--;
        }
        votos--;
    }

    public void trocaVotos(Voto voto) {
        if (voto.getVoto() == 0) {
            votosPositivos--;
        } else {
            votosPositivos++;
        }
    }
}
