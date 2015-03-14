
import models.Usuario;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by leonardocc on 10/03/15.
 */
public class UsuarioTest {

    @Test
    public void testDeveCriarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@edu.br");
        usuario.setNome("Ze");
        usuario.setSenha("123");
        Assert.assertTrue(Portal.salvaUsuario(usuario));
    }

    @Test
    public void testTentaCadastrarUsuarioEmailInvalido() {
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@teste");
        usuario.setNome("Ze");
        usuario.setSenha("123");
        Assert.assertFalse(Portal.salvaUsuario(usuario));
    }

    @Test
    public void testTentaCadastrarUsuarioNomeInvalido() {
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@edu.br");
        usuario.setNome("");
        usuario.setSenha("123");
        Assert.assertFalse(Portal.salvaUsuario(usuario));
    }

    @Test
    public void testTentaCadastrarUsuarioSenhaInvalida() {
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@edu.br");
        usuario.setNome("Ze");
        usuario.setSenha("");
        Assert.assertFalse(Portal.salvaUsuario(usuario));
    }

    @Test
    public void testRecuperaUsuarioCadastradoSalvoNoBD() {
        Usuario usuario = new Usuario();
        usuario.setEmail("teste@edu.br");
        usuario.setNome("Ze");
        usuario.setSenha("123");
        Portal.salvaUsuario(usuario);
        Assert.assertNotNull(Portal.recuperaUsuario("teste@edu.br"));
    }
}
