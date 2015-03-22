import controllers.Portal;
import models.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by X on 14/03/2015.
 */
public class AvaliacaoTest extends AbstractTest{
    @Test
    public void testAdicionaAvaliacaoAUmTema() {
        Usuario usuario = Portal.recuperaUsuario("teste@teste.com");
        Tema tema = Portal.recuperaTemaPeloNome("Projeto");
        Avaliacao avaliacao = new Avaliacao(usuario, tema);
        avaliacao.setValor(0);
        Assert.assertTrue(Portal.adicionaAvaliacao(avaliacao));
    }

    @Test
    public void testRecuperaAvaliacaoDeUmTemaPorUsuario() {
        Usuario usuario = Portal.recuperaUsuario("teste@teste.com");
        Tema tema = Portal.recuperaTemaPeloNome("Projeto");
        Avaliacao avaliacao = new Avaliacao(usuario, tema);
        avaliacao.setValor(0);
        Portal.adicionaAvaliacao(avaliacao);
        Assert.assertNotNull(Portal.recuperaAvaliacao(usuario.getId(), tema.getID()));
    }

    @Test
    public void testRecuperaAvaliacaoDeUmTemaPorUsuarioSemAvaliar() {
        Usuario usuario2 = Portal.recuperaUsuario("teste2@teste.com");
        Tema tema = Portal.recuperaTemaPeloNome("Projeto");
        Assert.assertNull(Portal.recuperaAvaliacao(usuario2.getId(), tema.getID()));
    }

    @Test
    public void testRecuperaMediaDasAvaliacoes() {
        Usuario usuario = Portal.recuperaUsuario("teste@teste.com");
        Tema tema = Portal.recuperaTemaPeloNome("Projeto");
        Avaliacao avaliacao = new Avaliacao(usuario, tema);
        avaliacao.setValor(2);
        Portal.adicionaAvaliacao(avaliacao);

        Usuario usuario2 = Portal.recuperaUsuario("teste2@teste.com");
        avaliacao = new Avaliacao(usuario2, tema);
        avaliacao.setValor(1);
        Portal.adicionaAvaliacao(avaliacao);

        Assert.assertTrue(Portal.recuperaMediaDeAvaliacoes(tema.getID()) == 1.5f);
    }

    @Test
    public void testRecuperaMedianaDasAvaliacoes() {
        Usuario usuario = Portal.recuperaUsuario("teste@teste.com");
        Tema tema = Portal.recuperaTemaPeloNome("Projeto");
        Avaliacao avaliacao = new Avaliacao(usuario, tema);
        avaliacao.setValor(-2);
        Portal.adicionaAvaliacao(avaliacao);

        Usuario usuario2 = Portal.recuperaUsuario("teste2@teste.com");
        avaliacao = new Avaliacao(usuario2, tema);
        avaliacao.setValor(1);
        Portal.adicionaAvaliacao(avaliacao);

        Usuario usuario3 = Portal.recuperaUsuario("teste3@teste.com");
        avaliacao = new Avaliacao(usuario3, tema);
        avaliacao.setValor(1);
        Portal.adicionaAvaliacao(avaliacao);

        Assert.assertTrue(Portal.recuperaMedianaDeAvaliacoes(tema.getID()) == 1.0f);
    }
}
