package controllers;

import models.Disciplina;
import models.Tema;
import models.Usuario;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.dashboard;
import views.html.dashboardMenu;
import views.html.dashboardTimeline;

import java.util.List;


public class AppController extends Controller {

    private static Usuario u;
    private static Disciplina d;
    private static Tema t;

    /**
     * This method render register page
     *
     * @return
     */
    //private static GenericDAO dao = new GenericDAO();
    @Transactional
    public static Result escolhaDisciplina() {
        DynamicForm requestData = Form.form().bindFromRequest();
        final String select, email;
        select = requestData.get("disciplina");
        email = session().get("email");
        u = Portal.recuperaUsuario(email);
        List<Disciplina> li = Portal.getListaDisciplinas();
        d = Portal.getDisciplinaNoBD("nome", select);
        if (select!=null && select.equals("SI1") && u != null) {
            return ok(dashboardMenu.render(d.getTemas(), u, li, d));
        }
        return ok(dashboard.render(li, u));
    }

    public static Result escolhaTema() {
        DynamicForm requestData = Form.form().bindFromRequest();
        Long id = Long.getLong(requestData.get("tema"));
        for (Tema tema : d.getTemas()) {
            if (tema.getID() == id) {
                t = tema;
            }
        }
        if (t != null) {
            return ok(dashboardTimeline.render(d.getTemas(),u,Portal.getListaDisciplinas(),d,t,t.getDicas()));
        } else {
            return ok(dashboardMenu.render(d.getTemas(), u, Portal.getListaDisciplinas(), d));
        }
    }

}
