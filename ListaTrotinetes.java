
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.*;
import java.util.Map.Entry;


public class ListaTrotinetes {

    private static Map<Trotinete, coordenadas> trotinetes;
    private ReentrantLock l = new ReentrantLock();


    public ListaTrotinetes(){
        trotinetes=new HashMap<>();
    }


    public boolean addTrotinete(Trotinete t) throws IOException{
        l.lock();
        try{
            boolean existetrotinete=trotinetes.containsKey(t.getLocation());
            if(!existetrotinete)
                trotinetes.put(t, t.getLocation());
            return existetrotinete;
        }finally {
            l.unlock();
        }
    }

    public void removeTrotinete(String numreserva){
        l.lock();
        for(Trotinete t: trotinetes.keySet()) {
            if (t.getNumreserva() == numreserva) {
                trotinetes.remove(t);
            }
        }
        l.unlock();
    }

    public List<coordenadas> getLocsTrotinetesLivres(){
        Map<Trotinete, coordenadas> Trotlivres = new HashMap<>();
        List<coordenadas> res = new ArrayList<coordenadas>();

        for(Trotinete t: trotinetes.keySet()) {
            if(t.getEstado() ==0) {
                Trotlivres.put(t, t.getLocation());
            }
        }

        l.lock();
        try {
            for(coordenadas c:Trotlivres.values()) {
                res.add(c);
            }
            return res;
        }finally {
            l.unlock();
        }
    }


        public static List<Trotinete> getTrotinetebyLoc(coordenadas c) throws IOException{
            List<Trotinete> trot = new ArrayList<Trotinete>();
            for (Entry<Trotinete, coordenadas> entry : trotinetes.entrySet()) {
                if (entry.getValue() == c) {
                    Trotinete t = entry.getKey();
                    trot.add(t);
                }
            }
            return trot;
        }

    public List<Trotinete> procurartrotinete(coordenadas c) {
        int distancia = 1000;
        int d_aux;
        int locX = c.getX();
        int locY = c.getY();
        coordenadas coord = null;

        // ir buscar todas as coordenadas das trotinetes paara calcular a distancia
        List<coordenadas> listaCoord = new ArrayList<>();
        listaCoord = getLocsTrotinetesLivres();

        //percorrer a lista e fazer distancia, guarda a coordeandaa da trotinete mais proxima
        for (coordenadas coor : listaCoord) {
            d_aux = coor.calculardistancia(locX, locY);
            if (d_aux < distancia) {
                distancia = d_aux;
                coord = coor;
            }
        }

        //Vai buscar a trotinete/trotinetes mais proximas mais proxima
        List<Trotinete> t;
        try {
            t = ListaTrotinetes.getTrotinetebyLoc(coord);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return t;
    }


}
