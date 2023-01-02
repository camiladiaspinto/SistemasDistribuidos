import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Map;

public class Demultiplexer  implements AutoCloseable {
    private Lock lock = new ReentrantLock(); //lock para proteger o acesso ao mapa
    private IOException exception = null;
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String nick;
    private DataOutputStream out;

    public Demultiplexer(Socket socket) throws IOException {
        this.socket=socket;
        this.bufferedWriter= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.bufferedReader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        nick= bufferedReader.readLine();
    }

    public void run() throws IOException {
        int x;
        int y;
        while (!socket.isClosed()){
            x= Integer.parseInt(bufferedReader.readLine());
            y= Integer.parseInt(bufferedReader.readLine());

            sendCoordenadas(nick,x,y);
        }
    }

    public void sendCoordenadas(String nick,int x, int y) throws IOException{
        lock.lock();
        try{
            out.writeUTF(nick);
            out.writeInt(x);
            out.writeInt(y);

            out.flush();
        }finally {
            lock.unlock();
        }
    }

    @Override
    public void close() throws Exception {
        socket.close();
    }
}
