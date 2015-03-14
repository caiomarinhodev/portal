package models;

/**
 * Created by X on 14/03/2015.
 */
public class DicaConhecimento extends Dica {

    private String nome;

    public DicaConhecimento(Usuario autor) {
        super(autor);
    }

    @Override
    public void setConselho(String conselho) {
    }

    @Override
    public void setURL(String url) {
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public void setRazao(String razao) {
    }

    public String getNome() {
        return nome;
    }
}
