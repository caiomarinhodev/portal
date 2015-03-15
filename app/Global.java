import models.*;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.db.jpa.JPA;

import java.util.ArrayList;
import java.util.Date;
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
                    Usuario u = new Usuario("teste@teste.com", "teste", "Teste");
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
                    Disciplina disciplina = new Disciplina("SI1",li);

                    dao.persist(u);

                    dao.persist(t1);
                    dao.persist(t2);
                    dao.persist(t3);
                    dao.persist(t4);
                    dao.persist(t5);
                    dao.persist(t6);
                    dao.persist(t7);
                    dao.persist(t8);
                    dao.persist(t9);
                    dao.persist(t10);
                    dao.persist(t11);
                    dao.persist(t12);

                    dao.persist(disciplina);

                    Dica dica = new Dica(u,new Date(),t1);
                    dica.setConhecimento("Essa parte é muito importante. Estude muito!");
                    dao.persist(dica);
                    Logger.info("Inserindo dados no BD.");
                    dao.flush();
                }


            }
        });
    }

    public void onStop(Application app) {
        Logger.info("desligada...");
    }

}