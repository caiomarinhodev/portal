package models;

import javax.persistence.*;

/**
 * Created by X on 14/03/2015.
 */
@Entity
@Table(name = "DICACONHECIMENTO")
public class DicaConhecimento extends Dica {
    @Column
    private String nome;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDicaConhecimento;

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

    @Override
    public String getConteudo() {
        return getNome();
    }

    public String getNome() {
        return nome;
    }
}
