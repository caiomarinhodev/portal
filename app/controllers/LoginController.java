package controllers;

import models.GenericDAO;
import models.Usuario;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.dashboard;
import views.html.login;

import java.util.List;

public class LoginController extends Controller {

    /**
     * This method render login
     * @return
     */
    private static GenericDAO dao = new GenericDAO();

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

}
