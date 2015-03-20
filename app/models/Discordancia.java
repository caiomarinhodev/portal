package models;

import javax.persistence.*;

/**
 * Created by caio on 20/03/15.
 */
@Entity
@Table(name = "DISCORDANCIA")
public class Discordancia {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDiscordancia;
    @Column
    private String nomeAutor;
    @Column
    private Long idDica;
    @Column
    private String razao;

    public Discordancia(String razao, String nomeAutor, Long idDica) {
        this.razao = razao;
        this.nomeAutor = nomeAutor;
        this.idDica = idDica;
    }

    public Discordancia(){

    }

    public Long getIdDiscordancia() {
        return idDiscordancia;
    }

    public void setIdDiscordancia(Long idDiscordancia) {
        this.idDiscordancia = idDiscordancia;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public Long getIdDica() {
        return idDica;
    }

    public void setIdDica(Long idDica) {
        this.idDica = idDica;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }
}
