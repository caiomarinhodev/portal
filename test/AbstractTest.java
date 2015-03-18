import javax.persistence.EntityManager;

import controllers.Portal;
import models.*;
import org.junit.After;
import org.junit.Before;
import play.GlobalSettings;
import play.db.jpa.JPA;
import play.db.jpa.JPAPlugin;
import play.test.FakeApplication;
import play.test.Helpers;
import scala.Option;

import java.security.NoSuchAlgorithmException;

/**
 * Created by X on 17/03/2015.
 */
public abstract class AbstractTest {

    public EntityManager em;

    @Before
    public void setUp() throws NoSuchAlgorithmException {
        FakeApplication app = Helpers.fakeApplication(new GlobalSettings());
        Helpers.start(app);
        Option<JPAPlugin> jpaPlugin = app.getWrappedApplication().plugin(JPAPlugin.class);
        em = jpaPlugin.get().em("default");
        JPA.bindForCurrentThread(em);
        em.getTransaction().begin();
        iniciaInstancias();
    }

    @After
    public void tearDown() {
        em.getTransaction().commit();
        JPA.bindForCurrentThread(null);
        em.close();
    }

    private void iniciaInstancias() throws NoSuchAlgorithmException {
        Usuario usuario = new Usuario("teste@teste.com", "teste", "Teste");
        Usuario usuario2 = new Usuario("teste2@teste.com", "teste", "Teste");
        Usuario usuario3 = new Usuario("teste3@teste.com", "teste", "Teste");
        Tema tema = new Tema("Projeto");
        Portal.salvaUsuario(usuario);
        Portal.salvaUsuario(usuario2);
        Portal.salvaUsuario(usuario3);
        Portal.adicionaTema(tema);
    }
}
