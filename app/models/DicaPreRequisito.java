package models;

/**
 * Created by X on 14/03/2015.
 */
public class DicaPreRequisito extends Dica {

    private String nome;
    private String razao;

    public DicaPreRequisito(Usuario autor) {
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
        this.razao = razao;
    }

    @Override
    public String getConteudo() {
        return getNome() + " \n "+ getRazao();
    }

    public String getNome() {
        return nome;
    }

    public String getRazao() {
        return razao;
    }
}
