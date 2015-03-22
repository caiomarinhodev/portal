package controllers;

import models.*;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.dashboard;
import views.html.dashboardMenu;
import views.html.dashboardTimeline;

import java.util.Date;
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

    @Transactional
    public static Result addVoto(Long idv){
        Dica dica = Portal.recuperaDica(idv);
        Portal.adicionaVoto(u,dica,1);
        return ok(dashboardTimeline.render(d.getTemas(), u, Portal.getListaDisciplinas(),
                d, t, Portal.recuperaDicasPorTema(t.getID()), Portal.recuperaMetaDicasPorDisciplina(d)));
    }

    @Transactional
    public static Result decrementaVoto(Long idvd){
        Dica dica = Portal.recuperaDica(idvd);
        Portal.adicionaVoto(u,dica,0);
        return ok(dashboardTimeline.render(d.getTemas(), u, Portal.getListaDisciplinas(), d,
                t, Portal.recuperaDicasPorTema(t.getID()), Portal.recuperaMetaDicasPorDisciplina(d)));
    }

    @Transactional
    public static Result addDiscordanciaADica(){
        DynamicForm requestData = Form.form().bindFromRequest();
        Long idDica = Long.parseLong(requestData.get("iddadica"));
        Long idUser = Long.parseLong(requestData.get("iduser"));
        String razao = requestData.get("razao");

        Portal.adicionaDiscordanciaEmUmaDica(idUser,idDica,razao);
        return decrementaVoto(idDica);
    }

    @Transactional
    public static Result addAvaliacao(){
        DynamicForm requestData = Form.form().bindFromRequest();
        int aval = Integer.parseInt(requestData.get("avaliacao"));
        Logger.info("AVAL: "+aval);
        Avaliacao a = new Avaliacao(u, t);
        a.setValor(aval);
        if(Portal.adicionaAvaliacao(a)){
            return escolhaTema(t.getID());
        }
        return ok(dashboardTimeline.render(d.getTemas(), u, Portal.getListaDisciplinas(),
                d, t, Portal.recuperaDicasPorTema(t.getID()), Portal.recuperaMetaDicasPorDisciplina(d)));
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
            return ok(dashboardTimeline.render(d.getTemas(), u, Portal.getListaDisciplinas(),
                    d, t, Portal.recuperaDicasPorTema(id), Portal.recuperaMetaDicasPorDisciplina(d)));
        } else {
            return ok(dashboardMenu.render(d.getTemas(), u, Portal.getListaDisciplinas(), d));
        }
    }

    @Transactional
    public static Result denunciaDica(){
        DynamicForm requestData = Form.form().bindFromRequest();
        Long idUser = Long.parseLong(requestData.get("idusuario"));
        Long idDica = Long.parseLong(requestData.get("iddica"));
        if(Portal.denunciaDica(idUser, idDica)){
            return escolhaTema(t.getID());
        }else{
            return redirect("#");
        }
    }

    @Transactional
    public static Result addDica(){
        DynamicForm requestData = Form.form().bindFromRequest();
        String messagem = requestData.get("dica");
        String tipo = requestData.get("tipo");
        String conselho, conhecimento, material;
        conselho = "conselho";
        conhecimento = "conhecimento";
        material = "material";

        Dica dica = new Dica(u,new Date(),t);

        if(tipo.toLowerCase().equals(conselho)){
            dica.setConselho(messagem);
        }else if(tipo.toLowerCase().equals(conhecimento)){
            dica.setConhecimento(messagem);
        }else if(tipo.toLowerCase().equals(material)){
            dica.setMaterial(messagem);
        }else{
            dica.setPreRequisito(messagem);
        }
        Portal.adicionaDica(dica);
        return ok(dashboardTimeline.render(d.getTemas(), u, Portal.getListaDisciplinas(), d,
                t, Portal.recuperaDicasPorTema(t.getID()), Portal.recuperaMetaDicasPorDisciplina(d)));

    }

    @Transactional
    public static Result addMeta(){
        DynamicForm requestData = Form.form().bindFromRequest();
        //String titulo = requestData.get("titulo");
        MetaDica md = new MetaDica(u,d);
        for(Dica d: Portal.recuperaDicasPorTema(t.getID())){
            if(requestData.get(String.valueOf(d.getDicaID()))!=null){
                Logger.info("VAlue: " + requestData.get(String.valueOf(d.getDicaID())));
                md.addDica(Portal.recuperaDica(d.getDicaID()));
            }
        }
        Portal.adicionaMetaDica(md);
        return ok(dashboardTimeline.render(d.getTemas(), u, Portal.getListaDisciplinas(), d,
                t, Portal.recuperaDicasPorTema(t.getID()), Portal.recuperaMetaDicasPorDisciplina(d)));

    }

}
