package tcp;

import tcp.TcpClient;
import tcp.TcpServer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Usage: ");
        String usage = scanner.nextLine();

        String[] splitUsage = usage.split(" ");
        List<String> usageList = new ArrayList<>(Arrays.asList(splitUsage));

        if (usageList.get(1).equals("-l")) { //Server
            Thread serverThread = new Thread(new TcpServer(Integer.parseInt(usageList.get(2))));
            serverThread.start();
        } else { //Client
            Thread clientThread = new Thread(new TcpClient(usageList.get(1), Integer.parseInt(usageList.get(2))));
            clientThread.start();
        }
    }
}
