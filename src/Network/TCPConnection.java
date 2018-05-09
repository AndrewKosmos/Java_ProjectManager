package Network;

import java.io.*;
import java.net.Socket;

/**
 * Created by Andrew on 02.05.2018.
 */
public class TCPConnection {
    private static TCPConnection ourInstance;
    private static String host;
    private static int port;
    private Socket socket;
    private OutputStreamWriter out;
    private InputStreamReader in;

    public static TCPConnection getInstance() {
        if(ourInstance == null){
            ourInstance = new TCPConnection();
        }
        return ourInstance;
    }

    private TCPConnection() {}

    public static String getHost() {
        return host;
    }

    public static void setHost(String host) {
        TCPConnection.host = host;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        TCPConnection.port = port;
    }

    public void initSocket() throws IOException {
        socket = new Socket(host,port);
        in = new InputStreamReader(socket.getInputStream());
    }

    public OutputStreamWriter getOut() {
        return out;
    }

    public void setOut(OutputStreamWriter out) {
        this.out = out;
    }

    public InputStreamReader getIn() {
        return in;
    }

    public void setIn(InputStreamReader in) {
        this.in = in;
    }

    public String sendAndRecieve(String query) throws IOException {
        OutputStream out = socket.getOutputStream();
        out.write(query.getBytes());

        int c;
        StringBuilder resultBuilder = new StringBuilder();
        while((c = in.read()) != '\n'){
            resultBuilder.append((char)c);
        }

        return resultBuilder.toString();
    }

    public void send(String query) throws IOException{
        OutputStream out = socket.getOutputStream();
        out.write(query.getBytes());
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        socket.close();
    }
}
