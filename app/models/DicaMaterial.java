package models;

/**
 * Created by X on 14/03/2015.
 */
public class DicaMaterial extends Dica {

    private String URL;

    public DicaMaterial(Usuario autor) {
        super(autor);
    }

    public String getURL() {
        return URL;
    }

    @Override
    public void setConselho(String conselho) {
    }

    @Override
    public void setURL(String url) {
        URL = url;
    }

    @Override
    public void setNome(String nome) {
    }

    @Override
    public void setRazao(String razao) {
    }

    @Override
    public String getConteudo() {
        return getURL();
    }
}
