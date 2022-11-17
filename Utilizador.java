import java.io.*;
//import java.util.*;

class Utilizador implements Serializable {
    private String name;
    private String palavrapasse;

    public Utilizador (String name, String palavrapasse) {
        this.name = name;
        this.palavrapasse=palavrapasse;
    }

    public String getname(){
        return name;
    }
    public String getpalavrapasse(){
        return palavrapasse;
    }

    public boolean validarpalavrapasse(String palavrapasse){
        return palavrapasse.equals(this.palavrapasse);
        
    }

    public String toString () {
        StringBuilder builder = new StringBuilder("{");
        builder.append(this.name).append(";");
        builder.append(this.palavrapasse).append(";");
        builder.append("}");
        return builder.toString();
    }

    public void serialize(DataOutputStream out) throws IOException {
        out.writeUTF(this.name);
        out.writeUTF(this.palavrapasse);
    }

    public static Utilizador deserialize(DataInputStream in) throws IOException {
        String name = in.readUTF();
        String palavrapasse=in.readUTF();
        return new Utilizador(name,palavrapasse);
    }
}