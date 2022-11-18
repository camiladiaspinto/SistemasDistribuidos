import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class cliente {

    //quando insiro dados tenho de meter out
    //out por causa dos writes 
    public static void main (String[] args) throws IOException {
        Socket socket = new Socket("localhost", 12345);

        BufferedReader stdin= new BufferedReader(new InputStreamReader(System.in));

        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

        

        String userInput;
        while (true) {
            System.out.println("1) Registar novo utilizador. \n 2) Iniciar sessão \n\n0) Sair\n\nDigite a opção pretendida:");
            userInput =stdin.readLine();
            int option = Integer.parseInt(userInput);
            out.writeInt(option);
            out.flush();
            switch (option) {
                case 1:
                    System.out.println("Registo:");
                    System.out.println("Nome do Utilizador:");
                    String nome=stdin.readLine();
                    System.out.println("Palavra-Passe:");
                    String palavrapasse=stdin.readLine();
                    out.writeUTF(nome);
                    out.flush();
                    out.writeUTF(palavrapasse);
                    out.flush();
                    break;
                case 2:
                    System.out.println("Login:");
                    System.out.println("Nome do Utilizador:");
                    String nome1=stdin.readLine();
                    System.out.println("Palavra-Passe:");
                    String palavrapasse2=stdin.readLine();
                    out.writeUTF(nome1);
                    out.flush();
                    out.writeUTF(palavrapasse2);
                    out.flush();
                    break;
                case 0:
                    socket.close();
                    return;
            }
        

        }

        
    }
}