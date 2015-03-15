package controllers;

import models.*;
import play.db.jpa.Transactional;

import java.util.List;

/**
 * Created by X on 14/03/2015.
 */
public class Portal {

    private static GenericDAO dao = new GenericDAO();

    @Transactional
    public static boolean salvaUsuario(Usuario usuario) {
        boolean operacao = dao.persist(usuario);
        dao.flush();
        return operacao;

    }

    @Transactional
    public static Usuario recuperaUsuario(String email) {
        List<Usuario> usuarios = dao.findByAttributeName(Usuario.class.getName(), "email", email);
        if (usuarios.size() > 0) {
            return usuarios.get(0);
        } else {
            return null;
        }
    }

    @Transactional
    public static boolean adicionaDica(Dica dica) {
        boolean operacao = dao.persist(dica);
        dao.flush();
        return operacao;
    }

    @Transactional
    public static void removerDica(Dica dica) {
        dao.removeById(Dica.class, dica.getDicaID());
        dao.flush();
    }

    @Transactional
    public static void atualizarDica(Dica newDica) {
        dao.merge(newDica);
        dao.flush();
    }

    @Transactional
    public static boolean adicionaAvaliacao(Avaliacao avaliacao) {
        return false;
    }

    @Transactional
    public static Avaliacao recuperaAvaliacao(Usuario usuario, Tema tema) {
        return null;
    }

    @Transactional
    public static float recuperaMediaDeAvaliacoes() {
        return 0.0f;
    }

    @Transactional
    public static float recuperaMedianaDeAvaliacoes() {
        return 0.0f;
    }

    @Transactional
    public static boolean adicionaVoto(Voto voto) {
        return false;
    }

    @Transactional
    public static boolean adicionaMetaDica(MetaDica dica) {
        return false;
    }

    @Transactional
    public static List<MetaDica> recuperaMetaDicasPorDisciplina(Disciplina disciplina) {
        return null;
    }

    @Transactional
    public static void denunciaDica(Dica dica) {
    }

    @Transactional
    public static List<Dica> recuperaDicasPorTema() {
        return null;
    }

    @Transactional
    public static void fechaDica(Dica dica) {
    }

    @Transactional
    public static List<Disciplina> getListaDisciplinas(){
        return dao.findAllByClassName(Disciplina.class.getName());
    }

    @Transactional
    public static List<Usuario> getListaDeUsuarios(){
        return dao.findAllByClassName(Usuario.class.getName());
    }

    @Transactional
    public static Disciplina getDisciplinaNoBD(String key, String value){
        List<Disciplina> l = dao.findByAttributeName(Disciplina.class.getName(),key,value);
        if(l.size()>0){
            return l.get(0);
        }else{
            return null;
        }
    }

}
