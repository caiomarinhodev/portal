package models;

/**
 * Created by X on 14/03/2015.
 */
public class Tema {

    private Long ID;
    private String nome;

    public Tema(String nome){
        this.nome = nome;
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
