
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * creating a client with a socket and a userName.
 * input and output streams for sending and receiving data.
 */
public class Client implements Runnable {
    private Socket socket;
    private DataOutputStream outputStream;
    private DataInputStream input;
    private String userName;

    /**
     * client constructor.
     * @param socket receives a socket,
     * @param userName receives a userName.
     * Trows IOException.
     */
    public Client(Socket socket, String userName) {
        this.socket = socket;
        try {
            outputStream = new DataOutputStream(socket.getOutputStream());
            input = new DataInputStream(socket.getInputStream());
            outputStream.writeUTF(userName);
            this.userName = userName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * while socket is connected, read message from console and parse it to json,
     * and send it to server.
     * Throws IOException.
     */
    public void sendMessage() {
        try {
            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                System.out.print("Enter your Message:  ");
                String message = scanner.nextLine();
                System.out.println();
                JSONObject json = new JSONObject();
                json.put("method", "message");
                json.put("messageText", message);
                outputStream.writeUTF(String.valueOf(json));
            }
        } catch (IOException e) {
            CLoseSocket();
        }
    }

    /**
     * while socket is connected, read and print the message from socket.
     * Throws IOException.
     */
    @Override
    public void run() {
        while (socket.isConnected()) {
            try {
                String message = input.readUTF();
                System.out.println(message);
            } catch (IOException e) {
                CLoseSocket();
            }
        }
    }

    /**
     * close socket, input stream and output stream.
     * Throws IOException.
     */
    private void CLoseSocket() {
        try {
            if (socket != null)
                socket.close();
            if (input != null)
                input.close();
            if (outputStream != null)
                outputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * creates a new socket with ip: localHost and port: 2020
     * creates a new client and a new thread.
     * sending the information we got from user to the server.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Socket socket1 = new Socket("localHost", 2020);
        Welcome welcome = new Welcome();
        welcome.menu();
        JSONObject json = welcome.getInformation();
        Client client = new Client(socket1, json.getString("userName"));
        new Thread(client).start();
        client.sendMessage();
    }
}
