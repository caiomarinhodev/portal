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
        Tema tema = new Tema("assuntoTeste");
        Dica dica = new Dica(autor, "14/03/2015", tema);
        dica.setConhecimento("teste java");
        Portal.adicionaDica(dica);
        Assert.assertNotNull(Portal.recuperaDicasPorTema());
        Portal.denunciaDica(dica);
        Portal.denunciaDica(dica);
        Portal.denunciaDica(dica);
        Assert.assertNull(Portal.recuperaDicasPorTema());
    }

    @Test
    public void testDeveFecharUmaDicaPorConcordancia() {
        Usuario autor = new Usuario();
        Tema tema = new Tema("assuntoTeste");
        Dica dica = new Dica(autor, "14/03/2015", tema);
        dica.setConhecimento("teste java");
        Portal.adicionaDica(dica);
        Assert.assertNotNull(Portal.recuperaDicasPorTema());
        Voto voto = new Voto(autor, dica, 1);
        for (int i= 0; i < 20; i++){
            dica.incrementaVotos(voto);
        }
        Assert.assertFalse(dica.isAberto());
    }
}
