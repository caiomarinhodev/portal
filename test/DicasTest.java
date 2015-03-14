import controllers.Portal;
import models.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by X on 14/03/2015.
 */
public class DicasTest {


    @Test
    public void testDeveAdicionarUmaDicaConhecimentoAUmTema() {
        Usuario autor = new Usuario();
        Tema tema = new Tema("assuntoTeste");
        Dica dica = new Dica(autor, "14/03/2015", tema);
        dica.setConhecimento("teste java");
        Assert.assertTrue(Portal.adicionaDica(dica));
    }

    @Test
    public void testDeveAdicionarUmaDicaPreRequisito() {
        Usuario autor = new Usuario();
        Tema tema = new Tema("assuntoTeste");
        Dica dica = new Dica(autor, "14/03/2015", tema);
        dica.setPreRequisito("testando");
        dica.setRazao("testee");
        Assert.assertTrue(Portal.adicionaDica(dica));
    }

    @Test
    public void testDeveAdicionarUmaDicaMaterial() {
        Usuario autor = new Usuario();
        Tema tema = new Tema("assuntoTeste");
        Dica dica = new Dica(autor, "14/03/2015", tema);
        dica.setMaterial("teste.java.com.br");
        Assert.assertTrue(Portal.adicionaDica( dica));
    }

    @Test
    public void testDeveAdicionarUmaDicaConselho() {
        Usuario autor = new Usuario();
        Tema tema = new Tema("assuntoTeste");
        Dica dica = new Dica(autor, "14/03/2015", tema);
        dica.setConselho("teste bla bla bla ...");
        Assert.assertTrue(Portal.adicionaDica( dica));
    }
}
