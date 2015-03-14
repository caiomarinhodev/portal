package controllers;

import models.*;
import play.db.jpa.Transactional;

/**
 * Created by X on 14/03/2015.
 */
public class Portal {

    @Transactional
    public static boolean salvaUsuario(Usuario usuario){
        return false;
    }

    @Transactional
    public static Usuario recuperaUsuario(String email){
        return null;
    }

    @Transactional
    public static boolean adicionaDicaAUmTema(Tema tema, Dica dica){
        return false;
    }

    @Transactional
    public static boolean adicionaAvaliacao(Avaliacao avaliacao){
        return false;
    }

    @Transactional
    public static Avaliacao recuperaAvaliacao(Usuario usuario, Tema tema){
        return null;
    }

    @Transactional
    public static float recuperaMediaDeAvaliacoes(){
        return 0.0f;
    }

    @Transactional
    public static float recuperaMedianaDeAvaliacoes(){
        return 0.0f;
    }

    @Transactional
    public static boolean adicionaVoto(Voto voto){
        return false;
    }

}
