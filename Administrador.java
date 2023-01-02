import java.io.*;
import java.net.Socket;

public class Administrador {

    public Administrador(Socket socket, Utilizador utilizador){

    }
    public static void main (String[] args) throws IOException {
        Socket socket = new Socket("localhost", 56789);

        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

        System.out.println("1 - Adicionar trotinetes");
        System.out.println("2 - Remover Trotinetes");
        System.out.println("0 - fechar");
        int serverInput = Integer.parseInt(stdin.readLine());

        switch (serverInput) {
            case 1:
                System.out.println("Numero de trotinete a adicionar");
                String numtrotinete = stdin.readLine();
                System.out.println("Localizaçao onde quer inserir a trotinete: \n    Variavel X:");
                String SLocX = stdin.readLine();
                int locX = Integer.parseInt(SLocX);
                System.out.println("Variavel Y:");
                String SLocY = stdin.readLine();
                int locY = Integer.parseInt(SLocY);
                System.out.println("Numero de reserva:");
                String numReserva = stdin.readLine();
                /*
                Trotinete t = new Trotinete(numtrotinete, locX, locY, numReserva, 0 );
                trotinetes.addTrotinete(t);
                */
                break;
            case 2 :
                System.out.println("Numero de trotinete pretende Remover");
                System.out.println("Numero de reserva:");
                /*
                numReserva = stdin.readLine();
                trotinetes.removeTrotinete(numReserva);
                */
                break;
            case 0 :
                socket.close();
                break;
        }
    }


}
