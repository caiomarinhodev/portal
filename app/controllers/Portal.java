package controllers;

import models.*;
import play.db.jpa.Transactional;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Comparator;
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
    public static boolean salvaUsuario(Usuario usuario) throws NoSuchAlgorithmException {
        if (validaEmail(usuario.getEmail()) && validaSenha(usuario.getSenha()) && validaNome(usuario.getNome())) {
            usuario.setSenha(encryptaSenha(usuario.getSenha()));
            boolean operacao = dao.persist(usuario);
            dao.flush();
            return operacao;
        }
        return false;
    }

    /**
     * Método que recupera um usuário pelo seu ID.
     *
     * @param ID Id a ser procurado;
     * @return Usuário, caso exista.
     */
    @Transactional
    public static Usuario recuperaUsuarioPorID(Long ID) {
        Usuario u = dao.findByEntityId(Usuario.class, ID);
        return u;
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
     * Método que adiciona uma nova dica no BD.
     *
     * @param dica dica a ser inserida
     * @return True se a dica foi adicionada, false cc.
     */
    @Transactional
    public static boolean adicionaDica(Dica dica) {
        if (validaDica(dica)) {
            boolean operacao = dao.persist(dica);
            dao.flush();
            return operacao;
        }
        return false;
    }

    /**
     * Método que remove uma dica.
     *
     * @param dica Dica a ser removida
     */
    @Transactional
    public static void removerDica(Dica dica) {
        dao.removeById(Dica.class, dica.getDicaID());
        dao.flush();
    }

    /**
     * Método que atualiza uma dica
     *
     * @param newDica Dica a ser atualizada.
     */
    @Transactional
    public static void atualizarDica(Dica newDica) {
        dao.merge(newDica);
        dao.flush();
    }

    /**
     * Recupera uma dica cadastrada pelo ID.
     *
     * @param id parametro de busca.
     * @return Dica, caso exista.
     */
    @Transactional
    public static Dica recuperaDica(long id) {
        return dao.findByEntityId(Dica.class, id);
    }

    /**
     * Método que adiciona uma avaliação no banco.
     *
     * @param avaliacao Avaliação a ser inserida.
     * @return True caso for inserida, false cc.
     */
    @Transactional
    public static boolean adicionaAvaliacao(Avaliacao avaliacao) {

        if (recuperaAvaliacao(avaliacao.getUsuario(), avaliacao.getTema()) == null) {
            boolean operacao = dao.persist(avaliacao);
            return operacao;
        } else {
            dao.merge(avaliacao);
        }
        dao.flush();
        return true;
    }

    /**
     * Método que recupera a avaliação de um usuário em um tema.
     *
     * @param usuario Usuário que avaliou.
     * @param tema    Tema avaliado.
     * @return Avaliação do usuário no tema.
     */
    @Transactional
    public static Avaliacao recuperaAvaliacao(long usuario, long tema) {

        List<Avaliacao> lista1 = dao.findByAttributeName(Avaliacao.class.getName(), "usuario", String.valueOf(usuario));
        List<Avaliacao> lista2 = dao.findByAttributeName(Avaliacao.class.getName(), "tema", String.valueOf(tema));
        lista1.retainAll(lista2);
        if (lista1.size() > 0) {
            return lista1.get(0);
        }
        return null;
    }

    /**
     * Metodo que recupera a média do valor de todas as avaliações.
     *
     * @return Média de todas as avaliações;
     */
    @Transactional
    public static float recuperaMediaDeAvaliacoes() {
        List<Avaliacao> avaliacoes = dao.findAllByClassName(Avaliacao.class.getName());
        float media = 0.0f;
        for (int i = 0; i < avaliacoes.size(); i++) {
            media += avaliacoes.get(i).getValor();
        }

        if (avaliacoes.size() > 0) {
            media = media / avaliacoes.size();
        }
        return media;
    }

    /**
     * Método que recupera a mediana das avaliações;
     *
     * @return Mediana dentre as avaliações.
     */
    @Transactional
    public static float recuperaMedianaDeAvaliacoes() {

        List<Avaliacao> avaliacoes = dao.findAllByClassName(Avaliacao.class.getName());
//        avaliacoes.sort(new Comparator<Avaliacao>() {
//            @Override
//            public int compare(Avaliacao o1, Avaliacao o2) {
//                if (o1.getValor() >= o2.getValor()) {
//                    return 1;
//                } else {
//                    return -1;
//                }
//            }
//        });

        float mediana = 0.0f;
        int size = avaliacoes.size();
        if (size > 0) {
            mediana = avaliacoes.get(0).getValor();
        }
        if (size % 2 == 0) {
            mediana = (avaliacoes.get(size / 2).getValor() + avaliacoes.get((size / 2) + 1).getValor()) / 2;
        } else {
            if (size > 1) {
                mediana = avaliacoes.get(size / 2).getValor();
            }
        }
        return mediana;
    }

    /**
     * Adiciona voto ao BD
     *
     * @param voto voto a ser adicionado
     * @return True se o voto for adicionado ou atualizado com sucesso.
     */
    @Transactional
    public static boolean adicionaVoto(Voto voto) {
        boolean operacao = false;
        if (validaVoto(voto)) {
            Voto votoBD = recuperaVotoPorUsuarioEmDica(voto.getUsuario(), voto.getDica());
            Dica dicaBD = recuperaDica(voto.getDica());
            if (votoBD == null) {
                operacao = dao.persist(voto);
                dicaBD.incrementaVotos(voto);
                dao.merge(dicaBD);
                return operacao;
            } else {
                if (votoBD.getVoto() == voto.getVoto()) {
                    dao.remove(votoBD);
                    dicaBD.decrementaVotos(voto);
                    dao.merge(dicaBD);
                } else {
                    dao.merge(voto);
                    dicaBD.trocaVotos(voto);
                    dao.merge(dicaBD);
                }
                operacao = true;
            }
            dao.flush();
        }
        return operacao;
    }

    /**
     * Método que recupera voto de usuário em uma dica.
     *
     * @param email  Usuário que votou.
     * @param dicaID Id da dica.
     * @return Voto do usuário.
     */
    public static Voto recuperaVotoPorUsuarioEmDica(String email, long dicaID) {
        List<Voto> votos1 = dao.findByAttributeName(Voto.class.getName(), "usuario", email);
        List<Voto> votos2 = dao.findByAttributeName(Voto.class.getName(), "dica", String.valueOf(dicaID));
        votos1.retainAll(votos2);
        if (votos1.size() > 0) {
            return votos1.get(0);
        } else {
            return null;
        }

    }

    /**
     * Método que valida um voto.
     *
     * @param voto Voto a ser validado.
     * @return True se for válida, false cc.
     */
    private static boolean validaVoto(Voto voto) {
        if (recuperaUsuario(voto.getUsuario()) != null && recuperaDica(voto.getDica()) != null) {
            return true;
        }
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
     * @param nome ID do tema a ser pesquisado.
     * @return um objeto di tipo Tema.
     */
    @Transactional
    public static Tema recuperaTemaPeloNome(String nome) {
        List<Tema> ld = dao.findByAttributeName(Tema.class.getName(), "nome", nome);
        if (ld.size() > 0) {
            return ld.get(0);
        } else {
            return null;
        }
    }

    /**
     * Metodo adiciona um Tema no BD.
     *
     * @param tema Tema a ser inserido.
     */
    @Transactional
    public static void adicionaTema(Tema tema) {
        if (validaNome(tema.getNome())) {
            dao.persist(tema);
            dao.flush();
        }
    }

    /**
     * Método queretorna uma sString contendo o conteúdo de uma dica.
     *
     * @param dicaID Dica de onde será retornado o conteúdo.
     * @return String contendo os atributos passados na dica.
     */
    public static String recuperaConteudoDeUmaDica(long dicaID) {

        Dica dica = dao.findByEntityId(Dica.class, dicaID);
        String conteudo = "";
        if (dica != null) {
            if (dica.getConhecimento() != "") {
                conteudo = "Conhecimento: " + dica.getConhecimento();
            }
            if (dica.getPreRequisito() != "") {
                conteudo = "Pré-requisito: " + dica.getPreRequisito() + " Razão: " + dica.getRazao();
            }
            if (dica.getMaterial() != "") {
                conteudo = "Material: " + dica.getMaterial();
            }
            if (dica.getConselho() != "") {
                conteudo = "Conselho: " + dica.getConselho();
            }
        }
        return conteudo;
    }

    /**
     * Método que gera o código MD5 referente a senha.
     *
     * @param senha String para que será codificada.
     * @return MD5 referente a string de entrada.
     */
    public static String encryptaSenha(String senha) throws NoSuchAlgorithmException {

        MessageDigest msg = MessageDigest.getInstance("MD5");
        msg.update(senha.getBytes(), 0, senha.length());
        return new BigInteger(1, msg.digest()).toString(16);
    }

    /**
     * Método que verifica se não existe email já cadastrado no banco de dados.
     *
     * @param email Chave a ser procurada.
     * @return True se não existir, false cc.
     */
    private static boolean validaEmail(String email) {

        if (email != null && recuperaUsuario(email) == null) {
            return true;
        }
        return false;
    }

    /**
     * Método que verifica se a senha não é nula nem vazia.
     *
     * @param senha Chave a ser verificada.
     * @return True se não for nula nem vazia, false cc.
     */
    private static boolean validaSenha(String senha) {
        if (senha != null && !senha.equals("")) {
            return true;
        }
        return false;
    }

    /**
     * Método que verifica se a nome não é nulo nem vazio.
     *
     * @param nome Chave a ser verificada.
     * @return True se não for nula nem vazia, false cc.
     */
    private static boolean validaNome(String nome) {
        if (nome != null && !nome.equals("")) {
            return true;
        }
        return false;
    }

    /**
     * Método que verifica se uma dica é válida.
     *
     * @param dica dica a ser verificada.
     * @return True se for válida, false cc.
     */
    private static boolean validaDica(Dica dica) {
        if (!dica.getConhecimento().equals("") || !dica.getPreRequisito().equals("") ||
                !dica.getRazao().equals("") || !dica.getConselho().equals("") ||
                (!dica.getMaterial().equals("") && validaURL(dica.getMaterial()))) {
            return true;
        }
        return false;
    }

    /**
     * Método que valida uma URL de acordo com um padrão pré definido.
     *
     * @param material URL a ser validada.
     * @return True se for válida, false cc.
     */
    private static boolean validaURL(String material) {
        String regra1 = "(http://)[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*(.com)";
        String regra2 = "(http://)[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*(.com.br)";
        String regra3 = "(http://)[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*(.edu)";
        String regra4 = "(http://)[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*(.edu.br)";

        if (material.matches(regra1) || material.matches(regra2) ||
                material.matches(regra3) || material.matches(regra4)) {
            return true;
        }
        return false;
    }
}
