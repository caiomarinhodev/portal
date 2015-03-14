import controllers.Portal;
import models.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by X on 14/03/2015.
 */
public class FlagsTest {
    @Test
    public void testDeveFecharUmaDicaPorDenuncia() {
        Usuario autor = new Usuario();
        Dica dica = new DicaConhecimento(autor);
        Tema tema = new Tema("assuntoTeste");
        dica.setNome("teste java");
        Portal.adicionaDicaAUmTema(tema, dica);
        Assert.assertNotNull(Portal.recuperaDicasPorTema());
        Portal.denunciaDica(dica);
        Portal.denunciaDica(dica);
        Portal.denunciaDica(dica);
        Assert.assertNull(Portal.recuperaDicasPorTema());
    }

    @Test
    public void testDeveFecharUmaDicaPorConcordancia() {
        Usuario autor = new Usuario();
        Dica dica = new DicaConhecimento(autor);
        Tema tema = new Tema("assuntoTeste");
        dica.setNome("teste java");
        Portal.adicionaDicaAUmTema(tema, dica);
        Assert.assertNotNull(Portal.recuperaDicasPorTema());
        for (int i= 0; i < 20; i++){
            dica.incrementaVotos();
        }
        Assert.assertFalse(dica.isAberto());
    }
}
