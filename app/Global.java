import controllers.Portal;
import models.*;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.db.jpa.JPA;

import java.util.ArrayList;
import java.util.List;


public class Global extends GlobalSettings {

    private static GenericDAO dao = new GenericDAO();

    @Override
    public void onStart(Application app) {
        Logger.info("inicializada...");

        JPA.withTransaction(new play.libs.F.Callback0() {

            public void invoke() throws Throwable {

                List<Dica> lis = dao.findAllByClassName(Dica.class.getName());
                if (lis.size() == 0) {
                    Usuario u = new Usuario("teste@teste.com", "teste", "Teste", "/assets/dist/img/avatar04.png");
                    Tema t1 = new Tema("Análise x Design");
                    Tema t2 = new Tema("Orientacao a Objetos");
                    Tema t3 = new Tema("GRASP");
                    Tema t4 = new Tema("GoF");
                    Tema t5 = new Tema("Arquitetura");
                    Tema t6 = new Tema("Play");
                    Tema t7 = new Tema("Javascript");
                    Tema t8 = new Tema("HTML+CSS+Bootstrap");
                    Tema t9 = new Tema("Heroku");
                    Tema t10 = new Tema("Labs");
                    Tema t11 = new Tema("Minitestes");
                    Tema t12 = new Tema("Projeto");
                    List<Tema> li = new ArrayList<Tema>();
                    li.add(t1);
                    li.add(t2);
                    li.add(t3);
                    li.add(t4);
                    li.add(t5);
                    li.add(t6);
                    li.add(t7);
                    li.add(t8);
                    li.add(t9);
                    li.add(t10);
                    li.add(t11);
                    li.add(t12);
                    Disciplina disciplina = new Disciplina("SI1", li);

                    Portal.salvaUsuario(u);
                    Portal.adicionaTema(t1);
                    Portal.adicionaTema(t2);
                    Portal.adicionaTema(t3);
                    Portal.adicionaTema(t4);
                    Portal.adicionaTema(t5);
                    Portal.adicionaTema(t6);
                    Portal.adicionaTema(t7);
                    Portal.adicionaTema(t8);
                    Portal.adicionaTema(t9);
                    Portal.adicionaTema(t10);
                    Portal.adicionaTema(t11);
                    Portal.adicionaTema(t12);
                    Portal.adicionaDisciplina(disciplina);

                    Logger.info("Inserindo dados no BD.");
                }
            }
        });
    }

    public void onStop(Application app) {
        Logger.info("desligada...");
    }

}