import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class cliente {

    public cliente(Socket socket,Utilizador utilizador){

    }
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
                    Utilizador user= new Utilizador(nome,palavrapasse);
                    cliente cl = new cliente(socket, user);
                    System.out.println("Insira a sua localização para procurar trotinete X:");
                    String locX=stdin.readLine();
                    out.write(Integer.parseInt(locX));
                    out.flush();
                    System.out.println("Y:");
                    String locY=stdin.readLine();
                    out.write(Integer.parseInt(locY));
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
                    Utilizador user1= new Utilizador(nome1,palavrapasse2);
                    cliente cl1 = new cliente(socket, user1);
                    System.out.println("Insira a sua localização para procurar trotinete X:");
                    locX=stdin.readLine();
                    out.write(Integer.parseInt(locX));
                    out.flush();
                    System.out.println("Y:");
                    locY=stdin.readLine();
                    out.write(Integer.parseInt(locY));
                    out.flush();
                    break;
                case 0:
                    socket.close();
                    return;
            }
        

        }

        
    }
}