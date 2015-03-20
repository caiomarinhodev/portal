package controllers;

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
import java.security.NoSuchAlgorithmException;

public class LoginController extends Controller {

    /**
     * This method render login
     *
     * @return
     */
    //private static GenericDAO dao = new GenericDAO();
    private static LoginFacebook loginFacebook = new LoginFacebook();

    @Transactional
    public static Result login() {
        return ok(login.render(""));
    }

    /**
     * This method render register page
     *
     * @return
     */
    @Transactional
    public static Result auth() throws NoSuchAlgorithmException {
        DynamicForm requestData = Form.form().bindFromRequest();
        final String email, senha;
        email = requestData.get("email");
        senha = requestData.get("senha");
        String md5 = Portal.encryptaSenha(senha);
        Usuario u = Portal.recuperaUsuario(email);
        if (u != null ) {
            if (md5.equals(u.getSenha())){
                session().clear();
                session("email", email);
                return app(u);
            } else {
                return ok(login.render("Senha inválida"));
            }
        }
        return ok(login.render("Usuario nao existe"));
    }

    /**
     * This method render a Dashboard Application
     *
     * @return
     */
    @Transactional
    private static Result app(Usuario usuario) {
        return ok(dashboard.render(Portal.getListaDisciplinas(), usuario));
    }


    /**
     * Método que chama URL do facebook onde o usuário poderá autorizar a aplicação
     * a acessar seus recursos privados.
     *
     * @return
     */
    //@RequestMapping("/loginfb")
    public static Result logarComFacebook() {
        return redirect(loginFacebook.getLoginRedirectURL());
    }

    /**
     * Executado quando o Servidor de Autorização fizer o redirect.
     *
     * @param code
     * @return
     * @throws IOException
     */
    //@RequestMapping("/loginfbresponse")
    @Transactional
    public static Result logarComFace(String code) throws IOException, NoSuchAlgorithmException {
        Logger.info("CODE:" + code);
        UsuarioFacebook ufb = loginFacebook.obterUsuarioFacebook(code);
        Usuario us = Portal.recuperaUsuario(ufb.getEmail());
        if(us==null){
            us = new Usuario(ufb.getEmail(),"12345", ufb.getName(),ufb.getPicture());
            Portal.salvaUsuario(us);
        }
        if (us!=null) {
            session().clear();
            session("email", us.getEmail());
            return ok(dashboard.render(Portal.getListaDisciplinas(),us));
        } else {
            return ok(login.render(""));
        }
    }

    @Transactional
    public static Result logarComGoogle(String email, String nome) throws IOException, NoSuchAlgorithmException {
        Logger.info("CODE:" + email);
        //UsuarioFacebook ufb = loginFacebook.obterUsuarioFacebook(code);
        Usuario us = Portal.recuperaUsuario(email);
        if(us==null){

            us = new Usuario(email,"12345", nome, "/assets/dist/img/avatar04.png");
            Portal.salvaUsuario(us);
        }
        if (us!=null) {
            session().clear();

            session("email", us.getEmail());
            return ok(dashboard.render(Portal.getListaDisciplinas(),us));
        } else {
            return ok(login.render(""));
        }

    }

}
