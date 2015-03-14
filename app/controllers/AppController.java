package controllers;

import models.GenericDAO;
import models.Usuario;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.dashboard;
import views.html.timeline;


public class AppController extends Controller {

    /**
     * This method render register page
     * @return
     */
    private static GenericDAO dao = new GenericDAO();
    @Transactional
    public static Result renderTimeline() {
        return ok(timeline.render("Portal"));
    }
    
}
