import java.io.*;
import java.net.*;

public class Client {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public Client(String address, int port) throws IOException {
        socket = new Socket(address, port);
        System.out.println("Connected to server: " + socket);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
    }

    public void calculateHypotenuse(double leg1, double leg2) throws IOException {
        out.writeDouble(leg1);
        out.writeDouble(leg2);
        double hypotenuse = in.readDouble();
        System.out.println("Hypotenuse: " + hypotenuse);
    }

    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client("localhost", 5000);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the value of leg1: ");
        double leg1 = Double.parseDouble(reader.readLine());
        System.out.println("Enter the value of leg2: ");
        double leg2 = Double.parseDouble(reader.readLine());
        client.calculateHypotenuse(leg1, leg2);
        client.close();
    }
}
