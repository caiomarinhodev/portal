package models;

import java.util.Date;

/**
 * Created by X on 14/03/2015.
 */
public abstract class Dica {

    private Usuario autor;
    private Date data;
    private int votos;
    private int denuncias;
    private boolean aberto;

    public Dica (Usuario autor){
        this.autor = autor;
        votos = 0;
        denuncias = 0;
        aberto = true;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public abstract void setConselho(String conselho);

    public abstract void setURL(String url);

    public abstract void setNome(String nome);

    public abstract void setRazao(String razao);

    public abstract String getConteudo();

    public int getVotos() {
        return votos;
    }

    public void incrementaVotos(){
        votos++;
    }

    public void incrementaDenuncias(){
        denuncias++;
    }

    public void decrementaVotos(){
        votos--;
    }

    public float getIndiceConcordancia(){
        return 0.0f;
    }

    public int getDenuncias() {
        return denuncias;
    }

    public boolean isAberto() {
        return aberto;
    }
}
