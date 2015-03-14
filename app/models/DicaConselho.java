package models;

/**
 * Created by X on 14/03/2015.
 */
public class DicaConselho extends Dica {

    public DicaConselho(Usuario autor){
        super(autor);
    }

    @Override
    public void setConselho(String conselho) {
        this.conselho = conselho;
    }

    @Override
    public void setURL(String url) {
    }

    @Override
    public void setNome(String nome) {
    }

    @Override
    public void setRazao(String razao) {
    }

    private String conselho;

    public String getConselho() {
        return conselho;
    }


}
