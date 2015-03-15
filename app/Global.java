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
                    Usuario u = new Usuario("caio@caio", "", "Teste");
                    Tema t1 = new Tema("Projeto");
                    Tema t2 = new Tema("LABS");
                    Tema t3 = new Tema("PIAZZA");
                    List<Tema> li = new ArrayList<Tema>();
                    li.add(t1);
                    li.add(t2);
                    li.add(t3);

                    Disciplina di = new Disciplina("SI1",li);
                    dao.persist(u);
                    dao.persist(t1);
                    dao.persist(t2);
                    dao.persist(t3);

                    dao.persist(di);
                    Dica d = new Dica(u,new Date(),t1);
                    dao.persist(d);
                }
                dao.flush();

            }
        });
    }

    public void onStop(Application app) {
        Logger.info("desligada...");
    }

}