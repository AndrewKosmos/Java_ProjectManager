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

    public String send(String query) throws IOException {
        OutputStreamWriter outStream = new OutputStreamWriter(socket.getOutputStream());
        InputStreamReader inStream = new InputStreamReader(socket.getInputStream());
        PrintWriter printWriter = new PrintWriter(out,true);
        printWriter.println(query);

        BufferedReader br = new BufferedReader(inStream);
        return br.readLine();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        socket.close();
    }
}
