package controllers;

import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.login;
import views.html.register;

public class Application extends Controller {

    /**
     * This method render Index
     *
     * @return
     */

    public static Result index() {
        return ok(index.render("Portal do Leite"));
    }

    /**
     * This method render login
     *
     * @return
     */
    public static Result login() {
        return ok(login.render(""));
    }

    /**
     * This method render register page
     *
     * @return
     */
    public static Result register() {
        return ok(register.render("Portal do Leite"));
    }

    public static Result signout() {
        session().clear();
        return ok(index.render("Portal do Leite"));
    }


}