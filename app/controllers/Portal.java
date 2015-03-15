package controllers;

import models.*;
import play.db.jpa.Transactional;

import java.util.List;

/**
 * Created by X on 14/03/2015.
 */
public class Portal {
    /**
     * Camada Generica de conexao com BD.
     */
    private static GenericDAO dao = new GenericDAO();

    /**
     * Metodo salva usuario no BD.
     *
     * @param usuario Objeto Usuario nao nulo.
     * @return true se salvar.
     */
    @Transactional
    public static boolean salvaUsuario(Usuario usuario) {
        boolean operacao = dao.persist(usuario);
        dao.flush();
        return operacao;

    }

    /**
     * Metodo retorna um Usuario do BD.
     *
     * @param email o email correspondente ao do usuario salvo no bd.
     * @return um objeto Usuario.
     */
    @Transactional
    public static Usuario recuperaUsuario(String email) {
        List<Usuario> usuarios = dao.findByAttributeName(Usuario.class.getName(), "email", email);
        if (usuarios.size() > 0) {
            return usuarios.get(0);
        } else {
            return null;
        }
    }

    /**
     * Metodo pesquisa e retorna um Usuario.
     *
     * @param id o id correspondente ao Usuario salvo no BD.
     * @return um objeto do tipo Usuario.
     */
    @Transactional
    public static Usuario pesquisaUsuario(Long id) {
        List<Usuario> li = dao.findByAttributeName(Usuario.class.getName(), "id", String.valueOf(id));
        if (li.size() > 0) {
            return li.get(0);
        } else {
            return null;
        }
    }

    /**
     * @param dica
     * @return
     */
    @Transactional
    public static boolean adicionaDica(Dica dica) {
        boolean operacao = dao.persist(dica);
        dao.flush();
        return operacao;
    }

    /**
     * @param dica
     */
    @Transactional
    public static void removerDica(Dica dica) {
        dao.removeById(Dica.class, dica.getDicaID());
        dao.flush();
    }

    /**
     * @param newDica
     */
    @Transactional
    public static void atualizarDica(Dica newDica) {
        dao.merge(newDica);
        dao.flush();
    }

    /**
     * @param avaliacao
     * @return
     */
    @Transactional
    public static boolean adicionaAvaliacao(Avaliacao avaliacao) {
        return false;
    }

    /**
     * @param usuario
     * @param tema
     * @return
     */
    @Transactional
    public static Avaliacao recuperaAvaliacao(Usuario usuario, Tema tema) {
        return null;
    }

    /**
     * @return
     */
    @Transactional
    public static float recuperaMediaDeAvaliacoes() {
        return 0.0f;
    }

    /**
     * @return
     */
    @Transactional
    public static float recuperaMedianaDeAvaliacoes() {
        return 0.0f;
    }

    /**
     * @param voto
     * @return
     */
    @Transactional
    public static boolean adicionaVoto(Voto voto) {
        return false;
    }

    /**
     * @param dica
     * @return
     */
    @Transactional
    public static boolean adicionaMetaDica(MetaDica dica) {
        return false;
    }

    /**
     * @param disciplina
     * @return
     */
    @Transactional
    public static List<MetaDica> recuperaMetaDicasPorDisciplina(Disciplina disciplina) {
        return null;
    }

    /**
     * @param dica
     */
    @Transactional
    public static void denunciaDica(Dica dica) {
    }

    /**
     * Metoo retorna uma lista de Dicas contidas em um determinado Tema.
     *
     * @param idTema id do tema de onde quer as dicas.
     * @return uma List de Dicas do Tema.
     */
    @Transactional
    public static List<Dica> recuperaDicasPorTema(Long idTema) {
        List<Dica> ld = dao.findByAttributeName(Dica.class.getName(), "temaID", String.valueOf(idTema));
        if (ld != null) {
            return ld;
        } else {
            return null;
        }
    }

    /**
     * @param dica
     */
    @Transactional
    public static void fechaDica(Dica dica) {
    }

    /**
     * Metodo get a lista de disciplinas salvas no BD
     *
     * @return um List de Disciplina.
     */
    @Transactional
    public static List<Disciplina> getListaDisciplinas() {
        return dao.findAllByClassName(Disciplina.class.getName());
    }

    /**
     * Metodo get a lista de Usuarios salvos no BD.
     *
     * @return um List de Usuario.
     */
    @Transactional
    public static List<Usuario> getListaDeUsuarios() {
        return dao.findAllByClassName(Usuario.class.getName());
    }

    /**
     * Metodo get uma Disciplina no BD.
     *
     * @param key   uma chave (atributo de disciplina) correpondente a disciplina.
     * @param value um valor correspondente a chave.
     * @return um objeto do tipo Disciplina.
     */
    @Transactional
    public static Disciplina getDisciplinaNoBD(String key, String value) {
        List<Disciplina> l = dao.findByAttributeName(Disciplina.class.getName(), key, value);
        if (l.size() > 0) {
            return l.get(0);
        } else {
            return null;
        }
    }

    /**
     * Metodo pesquisa e retorna um Tema no BD.
     *
     * @param id ID do tema a ser pesquisado.
     * @return um objeto di tipo Tema.
     */
    @Transactional
    public static Tema pesquisaTema(Long id) {
        List<Tema> ld = dao.findByAttributeName(Tema.class.getName(), "idTema", String.valueOf(id));
        if (ld.size() > 0) {
            return ld.get(0);
        } else {
            return null;
        }
    }

}
