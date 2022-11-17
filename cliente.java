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

        System.out.println("1) Adicionar novo utilizador.\n2) Criar novo utilizador \n\n0) Sair\n\nDigite a opção pretendida:");

        String userInput;
        while ((userInput =stdin.readLine()) != null) {
            int option = Integer.parseInt(userInput);
            out.writeInt(option);
            out.flush();
            switch (option) {
                case 1:
                    System.out.println("Registo:");
                    System.out.println("Nome do Utilizador:");
                    String nome1=stdin.readLine();
                    System.out.println("Palavra-Passe");
                    String palavrapasse1=stdin.readLine();
                    Utilizador utl1 = new Utilizador(nome1,palavrapasse1); 
                    out.flush();
                    //Utilizador newUtilizador = parseLine(userInput);
                    
                   // out.flush();
                  //  System.out.println(newUtilizador.toString());
                    break;
                case 2:
                    System.out.println("Registo:");
                    System.out.println("Nome do Utilizador:");
                    String nome=stdin.readLine();
                    System.out.println("Palavra-Passe");
                    String palavrapasse=stdin.readLine();
                    out.writeUTF(nome);
                    out.flush();
                    out.writeUTF(palavrapasse);
                    out.flush();
                    break;
                case 0:
                    socket.close();
                    return;
            }

        }

        socket.close();
        }
}