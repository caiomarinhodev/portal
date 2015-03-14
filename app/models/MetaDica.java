package models;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by X on 14/03/2015.
 */
public class MetaDica {
    private Usuario autor;
    private Date data;
    private Disciplina disciplina;
    List<Dica> dicas = new ArrayList<Dica>();
    private int votos;

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public List<Dica> getDicas() {
        return dicas;
    }

    public MetaDica (Usuario autor, Disciplina disciplina){
        this.autor = autor;
        this.disciplina = disciplina;
    }

    public Usuario getAutor() {
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
