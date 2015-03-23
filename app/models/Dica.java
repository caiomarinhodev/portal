package models;

import controllers.Portal;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by X on 14/03/2015.
 */


@Entity
@Table(name = "DICA")
public class Dica {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dicaID;

    @Column
    private long autorID;

    @Column
    private String conhecimento;

    @Column
    private String preRequisito;

    @Column
    private String razao;

    @Column
    private String material;

    @Column
    private String conselho;

    @Column
    private Date data;

    @Column
    private long temaID;

    @OneToMany
    private List<Discordancia> discordancias = new ArrayList<>();

    @Column
    private int votos;

    @Column
    private int votosPositivos;

    @Column
    private int denuncias;

    @Column
    private boolean aberto;

    public Dica() {

    }

    public Dica(Usuario autor, Date data, Tema tema) {
        this.autorID = autor.getId();
        this.conhecimento = "";
        this.preRequisito = "";
        this.razao = "";
        this.material = "";
        this.conselho = "";
        this.data = data;
        temaID = tema.getID();

        votos = 0;
        votosPositivos = 0;
        denuncias = 0;
        aberto = true;
    }

    public int getVotosPositivos() {
        return votosPositivos;
    }

    public void setConhecimento(String conhecimento) {
        this.conhecimento = conhecimento;
    }

    public void setPreRequisito(String preRequisito) {
        this.preRequisito = preRequisito;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setConselho(String conselho) {
        this.conselho = conselho;
    }

    public long getAutorID() {
        return autorID;
    }

    public String getConhecimento() {
        return conhecimento;
    }

    public String getPreRequisito() {
        return preRequisito;
    }

    public String getRazao() {
        return razao;
    }

    public String getMaterial() {
        return material;
    }

    public String getConselho() {
        return conselho;
    }

    public Date getData() {
        return data;
    }

    public long getTemaID() {
        return temaID;
    }

    public int getVotos() {
        return votos;
    }

    public int getDenuncias() {
        return denuncias;
    }

    public boolean isAberto() {
        return aberto;
    }

    public long getDicaID() {
        return dicaID;
    }

    public float getIndiceConcordancia() {
        if (votos > 0) {
            return ((float) votosPositivos) / ((float)votos);
        }
        return 0;
    }

    public void incrementaVotos(Voto voto) {
        if (isAberto()) {
            if (voto.getVoto() == 1) {
                votosPositivos++;
                if (getVotosPositivos() == 20){
                    aberto = false;
                }
            } else if (getVotos() - getVotosPositivos() > 19){
                aberto = false;
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

    public void incrementaDenuncias() {
        denuncias++;
    }

    public List<Discordancia> getDiscordancias() {
        return discordancias;
    }

    public void adicionaDiscordancia(Discordancia discordancia){
        discordancias.add(discordancia);
    }
}
