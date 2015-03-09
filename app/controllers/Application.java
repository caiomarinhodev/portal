package controllers;

import models.Usuario;
import play.*;
import play.db.jpa.Transactional;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    /**
     * This method render Index
     * @return
     */

    public static Result index() {
        return ok(index.render("Portal do Leite"));
    }

    /**
     * This method render login
     * @return
     */
    @Transactional
    public static Result login() {
        return ok(login.render("Portal do Leite"));
    }

    /**
     * This method render register page
     * @return
     */
    public static Result register() {
        return ok(register.render("Portal do Leite"));
    }

    public static Result signout(){
        session().clear();
        return ok(index.render("Portal do Leite"));
    }


}