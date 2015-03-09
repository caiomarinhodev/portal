package controllers;

import models.GenericDAO;
import models.Usuario;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;


public class RegisterController extends Controller {

    /**
     * This method render register page
     * @return
     */
    private static GenericDAO dao = new GenericDAO();
    @Transactional
    public static Result cadastraUsuario() {
        DynamicForm requestData = Form.form().bindFromRequest();
        final String email,nome,senha;
        email = requestData.get("email");
        nome = requestData.get("nome");
        senha = requestData.get("senha");
        Usuario u =new Usuario(email,senha,nome);
        dao.persist(u);
        dao.flush();
        return Application.login();
    }
    
}
