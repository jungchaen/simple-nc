package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class TcpServer implements Runnable {
    private ServerSocket serverSocket=null;
    private Socket socket=null;
    private  DataInputStream dataInputStream=null;
    private DataOutputStream dataOutputStream=null;

    private int portNumber;
    public TcpServer(int portNumber) {
        this.portNumber=portNumber;
    }

    @Override
    public void run() {
                try {
                    serverSocket = new ServerSocket(portNumber);
                    System.out.println("[Tcp Sever] Server is ready");

                    System.out.println("wait connecting");
                    socket = serverSocket.accept();
                    System.out.println("[TCP Server] " + socket.getInetAddress() + "로부터 연결요쳥이 들어왔습니다.");
                    while (true) {
                        receiveMessage();

                        /* 사용자 입력 */
                        Scanner scanner = new Scanner(System.in);
                        System.out.print("입력(STDIN) : ");
                        String senderMessage = scanner.nextLine();

                        sendMessage(senderMessage);
                    }
                } catch (SocketException e) {
                    System.exit(0);
                } catch (IOException e) {
                    System.out.println("[Tcp Server] " + e.getMessage());
                }
            }


    /*클라이언트로부터 메시지 수신*/
    public void receiveMessage() throws IOException {
        dataInputStream = new DataInputStream(socket.getInputStream());
        String message = dataInputStream.readUTF();

        System.out.println("[표준출력] "+message);
    }

    /*클라이언트로 메시지 전송*/
    public void sendMessage(String message) throws IOException {
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeUTF(message);
        dataOutputStream.flush();
        System.out.println("[Tcp Server] 사용자가 입력한 메시지("+message+")를 클라이언트로 전송");
    }
}
