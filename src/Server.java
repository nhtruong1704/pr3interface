import java.io.*;
import java.net.*;

public class Server {
    private ServerSocket serverSocket;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server is running and listening on port " + port);
    }

    public void run() throws IOException {
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                System.out.println("Client connected: " + socket);
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                double leg1 = in.readDouble();
                double leg2 = in.readDouble();
                double hypotenuse = calculateHypotenuse(leg1, leg2);
                out.writeDouble(hypotenuse);
                System.out.println("Hypotenuse calculated: " + hypotenuse);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (socket != null) {
                    socket.close();
                }
            }
        }
    }

    private double calculateHypotenuse(double leg1, double leg2) {
        double hypotenuse = Math.sqrt(leg1 * leg1 + leg2 * leg2);
        return hypotenuse;
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(5000);
        server.run();
    }
}
