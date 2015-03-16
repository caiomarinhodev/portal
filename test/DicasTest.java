import controllers.Portal;
import models.*;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Created by X on 14/03/2015.
 */
public class DicasTest {


    @Test
    public void testDeveAdicionarUmaDicaConhecimentoAUmTema() {
        Usuario autor = Portal.recuperaUsuario("teste@teste.com");
        Tema tema = Portal.recuperaTemaPeloNome("Projeto");
        Dica dica = new Dica(autor, new Date(), tema);
        dica.setConhecimento("teste java");
        Assert.assertTrue(Portal.adicionaDica(dica));
    }

    @Test
    public void testDeveAdicionarUmaDicaPreRequisito() {
        Usuario autor = Portal.recuperaUsuario("teste@teste.com");
        Tema tema = Portal.recuperaTemaPeloNome("Projeto");
        Dica dica = new Dica(autor, new Date(), tema);
        dica.setPreRequisito("testando");
        dica.setRazao("testee");
        Assert.assertTrue(Portal.adicionaDica(dica));
    }

    @Test
    public void testDeveAdicionarUmaDicaMaterial() {
        Usuario autor = Portal.recuperaUsuario("teste@teste.com");
        Tema tema = Portal.recuperaTemaPeloNome("Projeto");
        Dica dica = new Dica(autor, new Date(), tema);
        dica.setMaterial("teste.java.com.br");
        Assert.assertTrue(Portal.adicionaDica( dica));
    }

    @Test
    public void testDeveAdicionarUmaDicaMaterialInvalido() {
        Usuario autor = Portal.recuperaUsuario("teste@teste.com");
        Tema tema = Portal.recuperaTemaPeloNome("Projeto");
        Dica dica = new Dica(autor, new Date(), tema);
        dica.setMaterial("teste.java.br");
        Assert.assertTrue(Portal.adicionaDica( dica));
    }

    @Test
    public void testDeveAdicionarUmaDicaConselho() {
        Usuario autor = Portal.recuperaUsuario("teste@teste.com");
        Tema tema = Portal.recuperaTemaPeloNome("Projeto");
        Dica dica = new Dica(autor, new Date(), tema);
        dica.setConselho("teste bla bla bla ...");
        Assert.assertTrue(Portal.adicionaDica( dica));
    }
}
