import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
//import java.util.Objects;
//import java.util.concurrent.locks.ReentrantLock;

public class coordenadas implements Serializable {
    private int x;
    private int y;

    public coordenadas(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //função para calcular a distancia manhattan
    // public calculardistancia(int x1, int y1,int x2, int y2){
    //  return Math.abs(x1-x2) + Math.abs(y1-y2);
    //}


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        coordenadas that = (coordenadas) o;
        return x == that.x && y == that.y;
    }

    public void serialize(DataOutputStream out) throws IOException {
        out.writeInt(this.x);
        out.writeInt(this.y);
    }

    public static coordenadas deserialize(DataInputStream in) throws IOException {
        int x = in.readInt();
        int y=in.readInt();
        return new coordenadas(x,y);
    }

    public String toString() {
        StringBuilder res = new StringBuilder("");
        res.append("Coordenada (").append(x).append(",").append(y).append(")");
        return res.toString();
    }
}