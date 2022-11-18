
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.*;
//import java.util.concurrent.locks.ReentrantLock;


public class ListaUtilizadores {
    private Map<String,Utilizador> utilizadores;
    private ReentrantLock l = new ReentrantLock();
    
    public ListaUtilizadores(){
        utilizadores=new HashMap<>();
    }

    public boolean addUtilizador(Utilizador u) throws IOException{
        // se o utilizador não existir adiciona-o
        l.lock();
        try{       
            boolean existeutilizador=utilizadores.containsKey(u.getname());
            if(!existeutilizador)
                utilizadores.put(u.getname(),u);
                return existeutilizador;
        }finally {
            l.unlock();
        }
        
    }
    public boolean authUtilizador(Utilizador u){
        l.lock();
        try{
        Utilizador utilizador = utilizadores.get(u.getname());
        if(utilizador != null)
            return utilizador.validarpalavrapasse(u.getpalavrapasse());
        else
            return false;
        } finally{
            l.unlock();
        }
    }
}
