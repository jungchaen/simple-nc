package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class TcpClient implements Runnable{
    private Socket socket=null;
    private DataInputStream dataInputStream=null;
    private DataOutputStream dataOutputStream=null;
    private String host;
    private int portNumber;

    public TcpClient(String host, int portNumber) {
        this.host=host;
        this.portNumber=portNumber;
    }

    @Override
    public void run() {
                try {
                    socket = new Socket();
                    socket.connect(new InetSocketAddress(host,portNumber));
                    InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
                    System.out.println("[Tcp Client] connect success. host: "+isa.getHostString()+", port: "+isa.getPort());

                    while (true) {
                        /*사용자 입력*/
                        Scanner scanner = new Scanner(System.in);
                        System.out.print("입력(STDIN) : ");
                        String senderMessage = scanner.nextLine();

                        sendMessage(senderMessage);

                        receiveMessage();
                    }
                }catch (SocketException e) {
                    System.exit(0);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
    }


    /*서버로부터 메시지 수신*/
    public void receiveMessage() throws IOException {
        dataInputStream = new DataInputStream(socket.getInputStream());
        String receiveMessage = dataInputStream.readUTF();
        System.out.println("[표준출력] " + receiveMessage);
    }
    
    /*서버로 메시지 전송*/
    public void sendMessage(String message) throws IOException {
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeUTF(message);
        dataOutputStream.flush();
        System.out.println("[Tcp Client] 사용자가 입력한 메시지("+message+")를 서버에게 보냄");
    }
}
