package controllers;

import models.Usuario;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import java.security.NoSuchAlgorithmException;


public class RegisterController extends Controller {

    /**
     * This method render register page
     *
     * @return
     */
    //private static GenericDAO dao = new GenericDAO();
    @Transactional
    public static Result cadastraUsuario() throws NoSuchAlgorithmException {
        DynamicForm requestData = Form.form().bindFromRequest();
        final String email, nome, senha;
        email = requestData.get("email");
        nome = requestData.get("nome");
        senha = requestData.get("senha");
        Usuario u = new Usuario(email, senha, nome);
        if (Portal.salvaUsuario(u)) {
            return Application.login();
        }
        return Application.register();
    }

}
