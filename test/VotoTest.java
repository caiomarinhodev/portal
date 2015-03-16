import controllers.Portal;
import models.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Created by X on 14/03/2015.
 */
public class VotoTest {

    @Test
    public void testVotarEmUmaDica() {
        Usuario usuario = Portal.recuperaUsuario("teste@teste.com");
        Tema tema = Portal.recuperaTemaPeloNome("Projeto");
        Dica dica = new Dica(usuario, new Date(), tema);
        dica.setConhecimento("teste Voto");
        Portal.adicionaDica(dica);
        Voto voto = new Voto(usuario, dica, 1);
        Assert.assertTrue(Portal.adicionaVoto(voto));
    }

//    @Test
//    public void testVotarEmUmaDicaNovamente() {
//        Usuario usuario = Portal.recuperaUsuario("teste@teste.com");
//        Tema tema = Portal.recuperaTemaPeloNome("Projeto");
//        Dica dica = new Dica(usuario, new Date(), tema);
//        dica.setConhecimento("teste Voto");
//        Voto voto = new Voto(usuario, dica, 1);
//        Assert.assertTrue(Portal.adicionaVoto(voto));
//    }
//
//    @Test
//    public void testVerificaIncrementoContadorDeVotos() {
//        Usuario usuario = Portal.recuperaUsuario("teste@teste.com");
//        Tema tema = Portal.recuperaTemaPeloNome("Projeto");
//        Dica dica = new Dica(usuario, new Date(), tema);
//        Voto voto = new Voto(usuario, dica, 1);
//        Portal.adicionaVoto(voto);
//        Assert.assertTrue(dica.getVotos() > 0);
//    }
//
//    @Test
//    public void testRecuperaIndiceDeConcordancia() {
//        Usuario usuario = Portal.recuperaUsuario("teste@teste.com");
//        Tema tema = Portal.recuperaTemaPeloNome("Projeto");
//        Dica dica = new Dica(usuario, new Date(), tema);
//        Voto voto = new Voto(usuario, dica, 1);
//        Portal.adicionaVoto(voto);
//
//        Usuario usuario2 = Portal.recuperaUsuario("teste@teste.com");
//        dica = new Dica(usuario, new Date(), tema);
//        voto = new Voto(usuario2, dica, 1);
//        Portal.adicionaVoto(voto);
//
//        Usuario usuario3 = Portal.recuperaUsuario("teste@teste.com");
//        dica = new Dica(usuario, new Date(), tema);
//        voto = new Voto(usuario3, dica, 0);
//        Portal.adicionaVoto(voto);
//
//        Assert.assertTrue(dica.getIndiceConcordancia() == (2/3));
//    }
}
