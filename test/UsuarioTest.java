
import models.Usuario;
import org.junit.Assert;
import org.junit.Test;
import controllers.Portal;

import java.security.NoSuchAlgorithmException;

/**
 * Created by leonardocc on 10/03/15.
 */
public class UsuarioTest {

    @Test
    public void testDeveCriarUsuario() throws NoSuchAlgorithmException {
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@edu.br");
        usuario.setNome("Ze");
        usuario.setSenha("123");
        Assert.assertTrue(Portal.salvaUsuario(usuario));
    }

    @Test
    public void testTentaCadastrarUsuarioNomeInvalido() throws NoSuchAlgorithmException {
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@edu.br");
        usuario.setNome("");
        usuario.setSenha("123");
        Assert.assertFalse(Portal.salvaUsuario(usuario));
    }

    @Test
    public void testTentaCadastrarUsuarioSenhaInvalida() throws NoSuchAlgorithmException {
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@edu.br");
        usuario.setNome("Ze");
        usuario.setSenha("");
        Assert.assertFalse(Portal.salvaUsuario(usuario));
    }

    @Test
    public void testRecuperaUsuarioCadastradoSalvoNoBD() throws NoSuchAlgorithmException {
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@edu.br");
        usuario.setNome("Ze");
        usuario.setSenha("123");
        Portal.salvaUsuario(usuario);
        Assert.assertNotNull(Portal.recuperaUsuario("teste@edu.br"));
    }
}
