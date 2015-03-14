import controllers.Portal;
import models.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by X on 14/03/2015.
 */
public class MetaDicaTest {
    @Test
    public void testDeveAdicionarUmaMetaDicaAUmTema() {
        Usuario autor = new Usuario();
        Disciplina disciplina = new Disciplina();
        MetaDica meta = new MetaDica(autor, disciplina);
        Dica dica = new DicaConselho(autor);
        meta.addDica(dica);
        Assert.assertTrue(Portal.adicionaMetaDica(meta));
    }

    @Test
    public void testRecuperaMetaDicasPorDisciplina() {
        Usuario autor = new Usuario();
        Disciplina disciplina = new Disciplina();
        MetaDica meta = new MetaDica(autor, disciplina);
        Dica dica = new DicaConselho(autor);
        meta.addDica(dica);
        Portal.adicionaMetaDica(meta);


        Assert.assertNotNull(Portal.recuperaMetaDicasPorDisciplina(disciplina));
    }
}
