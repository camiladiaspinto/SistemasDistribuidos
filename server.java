
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.lang.model.util.ElementScanner6;
import java.lang.Iterable;


class ServerWorker implements Runnable {
    private Socket socket;
    public ListaUtilizadores lista;

    public ServerWorker (Socket socket, ListaUtilizadores lista) {
        this.lista= lista;
        this.socket = socket;
    }

    // @TODO
    @Override
    public void run() {
        try {
            DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            
            while (true) {
                int option = in.readInt();
                switch (option) {
                    case 1:
                        Utilizador newUtilizador= new Utilizador(in.readUTF(),in.readUTF()); 
                        lista.addUtilizador(newUtilizador);
                        System.out.println("entrei login");
                        System.out.println(newUtilizador.toString());
                        out.flush();
                        break;

                    case 2:
                
                        Utilizador newAuth= new Utilizador(in.readUTF(),in.readUTF());
                        System.out.println("entrei");
                        if(lista.authUtilizador(newAuth))
                            System.out.println("Autenticado");
                        else
                            System.out.println("Não autenticado");

                        break;
                    case 0:
                        throw new EOFException();
                }
            }

        } catch (EOFException e) {
            System.out.println("Connection closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


public class server {

    public static void main (String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        ListaUtilizadores utilizadorList = new ListaUtilizadores();

        while (true) {
            Socket socket = serverSocket.accept();
            Thread worker = new Thread(new ServerWorker(socket, utilizadorList));
            worker.start();
        }
    }

}