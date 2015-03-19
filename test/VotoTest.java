import controllers.Portal;
import models.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Created by X on 14/03/2015.
 */
public class VotoTest extends AbstractTest{

    @Test
    public void testVotarEmUmaDica() {
        Usuario usuario = Portal.recuperaUsuario("teste@teste.com");
        Tema tema = Portal.recuperaTemaPeloNome("Projeto");
        Dica dica = new Dica(usuario, new Date(), tema);
        dica.setConhecimento("teste Voto");
        Portal.adicionaDica(dica);
        Assert.assertTrue(Portal.adicionaVoto(usuario, dica, 1));
    }

    @Test
    public void testVotarEmUmaDicaNovamente() {
        Usuario usuario = Portal.recuperaUsuario("teste@teste.com");
        Tema tema = Portal.recuperaTemaPeloNome("Projeto");
        Dica dica = new Dica(usuario, new Date(), tema);
        dica.setConhecimento("teste Voto");
        Portal.adicionaDica(dica);
        Assert.assertTrue(Portal.adicionaVoto(usuario, dica, 1));
    }

    @Test
    public void testVerificaIncrementoContadorDeVotos() {
        Usuario usuario = Portal.recuperaUsuario("teste@teste.com");
        Tema tema = Portal.recuperaTemaPeloNome("Projeto");
        Dica dica = new Dica(usuario, new Date(), tema);
        dica.setConhecimento("teste Voto");
        Portal.adicionaDica(dica);
        Portal.adicionaVoto(usuario, dica, 1);
        Assert.assertTrue(dica.getVotos() > 0);
    }

    @Test
    public void testRecuperaIndiceDeConcordancia() {
        Usuario usuario = Portal.recuperaUsuario("teste@teste.com");
        Tema tema = Portal.recuperaTemaPeloNome("Projeto");
        Dica dica = new Dica(usuario, new Date(), tema);
        dica.setConhecimento("teste Voto");
        Portal.adicionaDica(dica);
        Portal.adicionaVoto(usuario, dica, 1);

        Usuario usuario2 = Portal.recuperaUsuario("teste@teste.com");
        dica = new Dica(usuario, new Date(), tema);
        dica.setConhecimento("teste Voto");
        Portal.adicionaDica(dica);
        Portal.adicionaVoto(usuario2, dica, 1);

        Usuario usuario3 = Portal.recuperaUsuario("teste@teste.com");
        dica = new Dica(usuario, new Date(), tema);
        dica.setConhecimento("teste Voto");
        Portal.adicionaDica(dica);
        Portal.adicionaVoto(usuario3, dica, 0);

        Assert.assertTrue(dica.getIndiceConcordancia() == (2/3));
    }
}
