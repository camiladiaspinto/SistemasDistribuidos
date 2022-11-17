
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
//import java.util.concurrent.locks.ReentrantLock;


public class ListaUtilizadores {
    private Map<String,Utilizador> utilizadores;
    
    public ListaUtilizadores(){
        utilizadores=new HashMap<>();
    }

    public boolean addUtilizador(Utilizador u) throws IOException{
        // se o utilizador não existir adiciona-o
        boolean existeutilizador=utilizadores.containsKey(u.getname());
            if(!existeutilizador)
                utilizadores.put(u.getname(),u);
                return existeutilizador;
        
    }

    public boolean autUtilizador(Utilizador u) throws IOException{
        boolean autenticado= false;
        if(utilizadores.containsKey(u.getname())== true)
            if(utilizadores.containsKey(u.getpalavrapasse())==true)
                autenticado = true;
                return autenticado;

    }
}