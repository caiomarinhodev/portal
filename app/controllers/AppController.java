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
     * Metodo static faz o controle de escolha do Usuario para a Disciplina Selecionada no Selector da View.
     *
     * @return Um Dashboard correspondente.
     */
    @Transactional
    public static Result escolhaDisciplina() {
        DynamicForm requestData = Form.form().bindFromRequest();
        final String select, email;
        select = requestData.get("disciplina");
        email = session().get("email");
        u = Portal.recuperaUsuario(email);
        List<Disciplina> li = Portal.getListaDisciplinas();
        d = Portal.getDisciplinaNoBD("nome", select);
        if (select != null && select.equals("SI1") && u != null) {
            return ok(dashboardMenu.render(d.getTemas(), u, li, d));
        }
        return ok(dashboard.render(li, u));
    }

    /**
     * Metodo faz o controle de escolha do usuário para o Tema selecionado nos itens mostrados.
     *
     * @param id id do tema escolhido.
     * @return Um dashboard com timeline.
     */
    @Transactional
    public static Result escolhaTema(Long id) {
        //DynamicForm requestData = Form.form().bindFromRequest();
        t = null;
        //Long i = Long.getLong(id));
        for (Tema tema : d.getTemas()) {
            if (tema.getID() == id) {
                t = tema;
            }
        }
        if (t != null) {
            return ok(dashboardTimeline.render(d.getTemas(), u, Portal.getListaDisciplinas(), d, t, Portal.recuperaDicasPorTema(id)));
        } else {
            return ok(dashboardMenu.render(d.getTemas(), u, Portal.getListaDisciplinas(), d));
        }
    }

//    @Transactional
//    public static Result addDica(){
//        DynamicForm requestData = Form.form().bindFromRequest();
//        String messagem = requestData.get("dica");
//
//    }

}
