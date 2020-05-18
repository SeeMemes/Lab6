package Server;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSide {

    /**
    * Точка входа в программу. Управляет подключением к клиентам и созданием потоков для каждого из них.
     * @param args массив по умолчанию в основном методе. Не используется здесь.
     */
    public static void main(String[] args) {
        CollectionManager serverCollection;
        try{
            serverCollection = new CollectionManager(args[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Изначальный путь не задан, будет создан файл New_File");
            String name = "New_File.xml";
            File file = new File(name);
            try{
                OutputStreamWriter filewriter = new OutputStreamWriter(new FileOutputStream(name));
                filewriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> \n<humanList>\n</humanList>");
                filewriter.close();
            } catch (IOException e1) {}
            serverCollection = new CollectionManager(name);
            serverCollection.getHumans().creationDate_Now();
        }
        try (ServerSocket server = new ServerSocket(8800)) {
            System.out.print("Сервер начал слушать клиентов. " + "\nПорт " + server.getLocalPort() +
                    " / Адрес " + InetAddress.getLocalHost() + ".\nОжидаем подключения клиентов ");
            Thread pointer = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.print(".");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.out.print("\n");
                        Thread.currentThread().interrupt();
                    }
                }
            });
            pointer.setDaemon(true);
            pointer.start();
            while (true) {
                Socket incoming = server.accept();
                pointer.interrupt();
                System.out.println(incoming + " подключился к серверу.");
                Runnable r = new ServerConnection(serverCollection, incoming);
                Thread t = new Thread(r);
                t.start();
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }
}