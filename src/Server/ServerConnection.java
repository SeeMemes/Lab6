package Server;

import Server.Command.*;
import Server.MyOwnClasses.HumanBeing;
import Server.MyOwnClasses.HumanList;
import Client.ServerRequest;
import Server.Tools.Converter;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Класс {@code ServerConnection} представляет объект сервера, который манипулирует {@link CollectionManager}.
 * @author Артемий Кульбако
 * @version 1.2
 * @since 28.04.19
 */
public class ServerConnection implements Runnable {

    private CollectionManager serverCollection;
    private Socket incoming;
    private HashMap<String, Command> commandList = new LinkedHashMap<>();

    /**
     * @param serverCollection обеспечивает доступ к коллекции.
     * @param incoming активное соединение с клиентской программой.
     * Команды, доступные клиенту, являются объектами {@link Command}, хранящимися в
     * {@code HashMap <String, Command> Commands}.
     */
    ServerConnection(CollectionManager serverCollection, Socket incoming) {
        this.serverCollection = serverCollection;
        this.incoming = incoming;
        LinkedHashMap<String, Command> commandList = new LinkedHashMap<>();
        HumanList humanList = serverCollection.getHumans();
        LinkedHashMap<Integer, HumanBeing> human = serverCollection.getHuman();
        commandList.put("you_cannot_save_from_client", new Save(human, "save", humanList));
        commandList.put("info", new Info(human, "info", humanList));
        commandList.put("exit", new Exit(human, "exit", humanList));
        commandList.put("help", new Help(human, "help", humanList));
        commandList.put("show", new Show(human, "show", humanList));
        commandList.put("clear", new Clear(human, "clear", humanList));
        commandList.put("update", new Update(human, "update", humanList));
        commandList.put("insert", new Insert(human, "insert", humanList));
        commandList.put("remove_key", new RemoveKey(human, "remove_key", humanList));
        commandList.put("execute_script", new ExecuteScript(human, "execute_script", humanList));
        commandList.put("replace_if_lowe", new ReplaceIfLowe(human, "replace_if_lowe", humanList));
        commandList.put("remove_lower_key", new RemoveLowerKey(human, "remove_lower_key", humanList));
        commandList.put("remove_greater_key", new RemoveGreaterKey(human, "remove_greater_key", humanList));
        commandList.put("sum_of_impact_speed", new SumOfImpactSpeed(human, "sum_of_impact_speed", humanList));
        commandList.put("average_of_impact_speed", new AverageOfImpactSpeed(human, "average_of_impact_speed", humanList));
        commandList.put("print_field_descending_mood", new PrintFieldDescendingMood(human, "print_field_descending_mood", humanList));
        this.commandList = commandList;
    }

    /**
     * Запускает активное соединение с клиентом в новом {@link Thread}.
     */
    @Override
    public void run() {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(reader);
        try (ObjectInputStream getFromClient = new ObjectInputStream(incoming.getInputStream());
             ObjectOutputStream sendToClient = new ObjectOutputStream(incoming.getOutputStream())) {
            sendToClient.writeObject("Соединение установлено.\nВы можете вводить команды.");
            Command errorCommand = new Command(null, "error", null) {
                @Override
                public String execute() {
                    return "Неизвестная команда. Введите 'help' для получения списка команд.";
                }
            };
            while (true) {
                try {
                    Thread th = new Thread(() -> {
                        while (true){
                            try {
                                String command = bufferedReader.readLine();
                                if (command.toLowerCase().equals("save")) {
                                    Save save = new Save(serverCollection.getHuman(), "save", serverCollection.getHumans());
                                    save.execute(serverCollection.getHuman(), "", serverCollection.getHumans(),
                                            serverCollection.getCollectionPath(), true);
                                    System.out.println("Файл сохранен");
                                }
                                else if (command.toLowerCase().equals("exit")) {
                                    commandList.get("you_cannot_save_from_client").execute();
                                    System.out.println("Завершение программы");
                                    System.exit(0);
                                }
                            } catch (InterruptedIOException e) {
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    th.start();
                    ServerRequest requestFromClient = (ServerRequest) getFromClient.readObject();
                    String commandRequest = requestFromClient.getCommand() + " " + requestFromClient.getArguments();
                    System.out.print("Получено [" + requestFromClient + "] от " + incoming + ". ");
                    String[] parsedCommand = commandRequest.trim().split(" ",2);

                    String response = "Команда " + commandList.getOrDefault(parsedCommand[0], errorCommand).getCommand() + " была выполнена";
                    String command_answer = "";
                    try{
                        LinkedHashMap<Integer, HumanBeing> humanMap = commandList.getOrDefault(parsedCommand[0], errorCommand).execute(
                                serverCollection.getHuman(),commandRequest,
                                serverCollection.getHumans(),serverCollection.getCollectionPath(), true);
                        serverCollection.setHuman(humanMap);
                        serverCollection.setHumans(Converter.convertToList(humanMap));
                        String answer = response + " " + command_answer;
                        sendToClient.writeObject(answer);
                    } catch (ClassCastException e) {
                        command_answer = commandList.getOrDefault(parsedCommand[0], errorCommand).execute(
                                serverCollection.getHuman(),commandRequest,
                                serverCollection.getHumans(),serverCollection.getCollectionPath());
                        sendToClient.writeObject(response + " \n" + command_answer);
                    }

                    System.out.println("Ответ успешно отправлен.");
                } catch (SocketException e) {
                    System.out.println(incoming + " отключился от сервера."); //Windows
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
            System.err.println(incoming + " отключился от сервера."); //Unix
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServerConnection that = (ServerConnection) o;
        return Objects.equals(serverCollection, that.serverCollection) &&
                Objects.equals(incoming, that.incoming) &&
                Objects.equals(commandList, that.commandList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serverCollection, incoming, commandList);
    }

    @Override
    public String toString() {
        return "ServerConnection{" +
                "serverCollection=" + serverCollection +
                ", incoming=" + incoming +
                ", commandList=" + commandList +
                '}';
    }
}