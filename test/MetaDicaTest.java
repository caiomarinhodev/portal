import controllers.Portal;
import models.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by X on 14/03/2015.
 */
public class MetaDicaTest extends AbstractTest{
    @Test
    public void testDeveAdicionarUmaMetaDicaAUmTema() {
        Usuario autor = Portal.recuperaUsuario("teste@teste.com");
        Disciplina disciplina = Portal.recuperaDisciplinaPeloNome("SI1Test");
        Tema tema = Portal.recuperaTemaPeloNome("Projeto");
        Dica dica = new Dica(autor, new Date(), tema);
        dica.setConselho("Estoutestando");
        Portal.adicionaDica(dica);
        MetaDica meta = new MetaDica(autor, disciplina);
        meta.addDica(Portal.recuperaDicasPorTema(tema.getID()).get(0));
        Assert.assertTrue(Portal.adicionaMetaDica(meta));
    }

    @Test
    public void testRecuperaMetaDicasPorDisciplina() {
        Usuario autor = Portal.recuperaUsuario("teste@teste.com");
        Disciplina disciplina = Portal.recuperaDisciplinaPeloNome("SI1Test");
        Tema tema = Portal.recuperaTemaPeloNome("Projeto");
        Dica dica = new Dica(autor, new Date(), tema);
        dica.setConselho("Estoutestando");
        Portal.adicionaDica(dica);
        MetaDica meta = new MetaDica(autor, disciplina);
        meta.addDica(Portal.recuperaDicasPorTema(tema.getID()).get(0));
        Portal.adicionaMetaDica(meta);
        Assert.assertNotNull(Portal.recuperaMetaDicasPorDisciplina(disciplina));
    }
}
