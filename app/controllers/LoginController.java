package controllers;

import models.GenericDAO;
import models.LoginFacebook;
import models.Usuario;
import models.UsuarioFacebook;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.dashboard;
import views.html.login;

import java.io.IOException;
import java.util.List;

public class LoginController extends Controller {

    /**
     * This method render login
     * @return
     */
    private static GenericDAO dao = new GenericDAO();
    private static LoginFacebook loginFacebook = new LoginFacebook();

    @Transactional
    public static Result login() {
        return ok(login.render("Portal do Leite"));
    }

    /**
     * This method render register page
     * @return
     */
    @Transactional
    public static Result auth() {
        DynamicForm requestData = Form.form().bindFromRequest();
        final String email,senha;
        email = requestData.get("email");
        senha = requestData.get("senha");

        //Usuario u = Usuario.find.where().eq("email", email).findUnique();
        List<Usuario> lista = dao.findAllByClassName(Usuario.class.getName());
        for(Usuario u: lista){
            if(u.getEmail().equals(email)&& u.getSenha().equals(senha)){
                session().clear();
                session("email", email);
                return app(u);
            }
        }
        return ok(login.render("Usuario nao existe"));
    }

    /**
     * This method render a Dashboard Application
     * @return
     */
    @Transactional
    private static Result app(Usuario usuario) {
        return ok(dashboard.render("Portal do Leite", usuario));
    }



    /**
     * Método que chama URL do facebook onde o usuário poderá autorizar a aplicação
     * a acessar seus recursos privados.
     * @return
     */
    //@RequestMapping("/loginfb")
    public static Result logarComFacebook(){
        return redirect(loginFacebook.getLoginRedirectURL());
    }

    /**
     * Executado quando o Servidor de Autorização fizer o redirect.
     * @param code
     * @return
     * @throws IOException
     */
    //@RequestMapping("/loginfbresponse")
    public static Result logarComFace(String code) throws IOException {
        Logger.info("CODE:"+code);
        UsuarioFacebook ufb = loginFacebook.obterUsuarioFacebook(code);
        Usuario u = new Usuario(ufb.getEmail(), ufb.getName(), ufb.getName());
        return ok(dashboard.render("Portal do Leite", u));
    }

}
