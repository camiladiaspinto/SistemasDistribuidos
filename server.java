import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;


class ServerWorker implements Runnable {
    private Socket socket;
    public ListaUtilizadores lista;
    public ListaTrotinetes listaTrot;
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
            coordenadas c;
            List<Trotinete> TrotinetesMaisProximas;

            while (true) {
                int option = in.readInt();
                switch (option) {
                    case 1:
                        Utilizador newUtilizador= new Utilizador(in.readUTF(),in.readUTF()); 
                        lista.addUtilizador(newUtilizador);
                        System.out.println("entrei login");
                        System.out.println(newUtilizador.toString());
                        out.flush();
                        c= new coordenadas(in.read(),in.read());
                        TrotinetesMaisProximas= listaTrot.procurartrotinete(c);

                        break;

                    case 2:
                        Utilizador newAuth= new Utilizador(in.readUTF(),in.readUTF());
                        System.out.println("entrei");
                        if(!lista.authUtilizador(newAuth)) {
                            System.out.println("Não autenticado");
                        }
                        else {
                            System.out.println("Autenticado");
                            c = new coordenadas(in.read(), in.read());
                            TrotinetesMaisProximas = listaTrot.procurartrotinete(c);

                        }
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


class ServerWorkerAdministrador implements Runnable {

    public ServerWorkerAdministrador() {  }

    public void run() {    }

}



public class server {

    public static void main (String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        ServerSocket ssAdministrador = new ServerSocket(56789);
        ListaUtilizadores utilizadorList = new ListaUtilizadores();
        ListaTrotinetes trotinetes = new ListaTrotinetes();
        BufferedReader stdin= new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            Socket socket = serverSocket.accept();
            Thread worker = new Thread(new ServerWorker(socket, utilizadorList));
            Demultiplexer clhandler= new Demultiplexer(socket);
            worker.start();


        }
    }
}