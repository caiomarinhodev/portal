import controllers.Portal;
import models.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * Created by X on 14/03/2015.
 */
public class FlagsTest extends AbstractTest {
    @Test
    public void testDeveFecharUmaDicaPorDenuncia() {
        Usuario autor = Portal.recuperaUsuario("teste@teste.com");
        Usuario autor2 = Portal.recuperaUsuario("teste2@teste.com");
        Usuario autor3 = Portal.recuperaUsuario("teste3@teste.com");
        Tema tema = Portal.recuperaTemaPeloNome("Projeto");
        Dica dica = new Dica(autor, new Date(), tema);
        dica.setConhecimento("teste java");
        Portal.adicionaDica(dica);
        Assert.assertNotNull(Portal.recuperaDicasPorTemaEUsuario(autor.getId(), tema.getID()));
        List<Dica> dicas = Portal.recuperaDicasPorTemaEUsuario(autor.getId(), tema.getID());
        dica = dicas.get(0);
        Portal.denunciaDica(autor.getId(), dica.getDicaID());
        Portal.denunciaDica(autor2.getId(), dica.getDicaID());
        Portal.denunciaDica(autor3.getId(), dica.getDicaID());
        Assert.assertTrue(Portal.recuperaDicasPorTema(tema.getID()) == null ||
                Portal.recuperaDicasPorTemaEUsuario(autor.getId(), tema.getID()).size() < dicas.size());
    }

    @Test
    public void testDeveFecharUmaDicaPorConcordancia() {
        Usuario autor = Portal.recuperaUsuario("teste@teste.com");
        Tema tema = new Tema("assuntoTeste");
        Portal.adicionaTema(tema);
        tema = Portal.recuperaTemaPeloNome("assuntoTeste");
        Dica dica = new Dica(autor, new Date(), tema);
        dica.setConhecimento("teste java");
        Portal.adicionaDica(dica);
        Assert.assertNotNull(Portal.recuperaDicasPorTema(tema.getID()));
        Voto voto = new Voto(autor, dica, 1);
        for (int i = 0; i < 20; i++) {
            dica.incrementaVotos(voto);
        }
        Assert.assertFalse(dica.isAberto());
    }
}
