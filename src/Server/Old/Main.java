package Server.Old; /**
 * ConsoleApp which allows to control the collection of Human Beings
 * Class Main
 *
 * @author Aleksandr Provotorov, P3110
 */

import Server.Exceptions.ExistanceException;
import Server.Exceptions.RightException;
import Server.MyOwnClasses.HumanBeing;
import Server.MyOwnClasses.HumanList;
import Server.Tools.Converter;
import Server.Tools.Parser;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String []args) throws IOException {
        CommandShell commandShell = new CommandShell();
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(reader);
        Scanner scanner = new Scanner(System.in);
        boolean b = true;
        String path = "";

        try {
            Parser Parser = new Parser();
            path = args[0];
            if (!(path == "")) Parser.parse(path);
            System.out.println("Файл был найден и конвертирован в коллекцию");
        } catch (ArrayIndexOutOfBoundsException | ExistanceException e) {
            File file = null;
            boolean choise = false;
            System.out.println("Файл не был найден. Заново ввести название файла / создать его? " +
                    "(true - заново ввести, false - создать)");
            boolean input = false;
            while (!input){
                try{
                    choise = scanner.nextBoolean();
                    input = true;
                } catch (InputMismatchException e1) {
                    System.out.println("Введите выбор правильно (true - заново ввести, false - создать):");
                    scanner.nextLine();
                }
            }
            if (choise){
                System.out.println("Введите название файла или \"exit\", чтобы выйти из программы: ");
                while (choise) {
                    String name = bufferedReader.readLine();
                    if (name.equals("exit")) System.exit(0);
                    file = new File(name);
                    try{
                        Parser Parser = new Parser();
                        Parser.parse(name);
                        choise = false;
                        path = file.getName();
                        System.out.println("Файл \"" + path +"\" был открыт");
                    } catch (RightException e1) {
                        System.out.println ("Отсутствуют права доступа к файлу, " +
                                "попробуйте изменить права доступа к файлу и ввести название файла заново " +
                                "или \"exit\", чтобы выйти из программы: ");
                    } catch (JAXBException e1) {
                        System.out.println("Данные в файле были записаны в неправильном формате," +
                                " попробуйте изменить содержание файла и ввести название файла заново " +
                                "или \"exit\", чтобы выйти из программы: ");
                    } catch (ExistanceException e1) {
                        System.out.println("Такого файла не существует, введите " +
                                "название файла заново или \"exit\", чтобы выйти из программы: ");
                    }
                }
            } else {
                System.out.println("Введите название файла или \"exit\", чтобы выйти из файла: ");
                String name = bufferedReader.readLine();
                if (name.equals("exit")) System.exit(0);
                file = new File(name);
                if (!file.exists()) file.createNewFile();
                else
                    while (file.exists()) {
                        if (!file.canRead() || !file.canWrite()) System.out.println("** Возникла ошибка с правами файла");
                        if (!file.canRead()) System.out.println("** Файл невозможно читать");
                        if (!file.canWrite()) System.out.println("** В файл невозможно писать");
                        System.out.println("Ошибка: данный файл существует. " +
                                "Введите название файла или \"exit\", чтобы выйти из файла:");
                        name = bufferedReader.readLine();
                        if (name.equals("exit")) System.exit(0);
                        file = new File(name);
                    }
                path = file.getPath();
                System.out.println("Файл \"" + path +"\" был создан");
            }
        } catch (RightException e) {
            File file = null;
            boolean choise = false;
            System.out.println("Возникла ошибка с правами доступа к файлу. Заново ввести название файла / создать его? " +
                    "(true - заново ввести, false - создать)");
            boolean input = false;
            while (!input){
                try{
                    choise = scanner.nextBoolean();
                    input = true;
                } catch (InputMismatchException e1) {
                    System.out.println("Введите выбор правильно (true / false):");
                    scanner.nextLine();
                }
            }
            if (choise){
                System.out.println("Введите название файла или \"exit\", чтобы выйти из программы: ");
                while (choise) {
                    String name = bufferedReader.readLine();
                    if (name.equals("exit")) System.exit(0);
                    file = new File(name);
                    try{
                        Parser Parser = new Parser();
                        Parser.parse(name);
                        choise = false;
                        path = file.getName();
                        System.out.println("Файл \"" + path +"\" был открыт");
                    } catch (RightException e1) {
                        System.out.println ("Отсутствуют права доступа к файлу, " +
                                "попробуйте изменить права доступа к файлу и ввести название файла заново " +
                                "или \"exit\", чтобы выйти из программы: ");
                    } catch (JAXBException e1) {
                        System.out.println("Данные в файле были записаны в неправильном формате," +
                                " попробуйте изменить содержание файла и ввести название файла заново " +
                                "или \"exit\", чтобы выйти из программы: ");
                    } catch (ExistanceException e1) {
                        System.out.println("Такого файла не существует, введите " +
                                "название файла заново или \"exit\", чтобы выйти из программы: ");
                    }
                }
            } else {
                System.out.println("Введите название файла или \"exit\", чтобы выйти из файла: ");
                String name = bufferedReader.readLine();
                if (name.equals("exit")) System.exit(0);
                file = new File(name);
                if (!file.exists()) file.createNewFile();
                else
                    while (file.exists()) {
                        if (!file.canRead() || !file.canWrite()) System.out.println("** Возникла ошибка с правами файла");
                        if (!file.canRead()) System.out.println("** Файл невозможно читать");
                        if (!file.canWrite()) System.out.println("** В файл невозможно писать");
                        System.out.println("Ошибка: данный файл существует. " +
                                "Введите название файла или \"exit\", чтобы выйти из файла:");
                        name = bufferedReader.readLine();
                        if (name.equals("exit")) System.exit(0);
                        file = new File(name);
                    }
                path = file.getPath();
                System.out.println("Файл \"" + path +"\" был создан");
            }
        } catch (JAXBException e) {
            File file = null;
            boolean choise = false;
            System.out.println("XML файл был сгенерирован неверно. Заново ввести название файла / создать его? " +
                    "(true - заново ввести, false - создать)");
            boolean input = false;
            while (!input){
                try{
                    choise = scanner.nextBoolean();
                    input = true;
                } catch (InputMismatchException e1) {
                    System.out.println("Введите выбор правильно (true / false):");
                    scanner.nextLine();
                }
            }
            if (choise){
                System.out.println("Введите название файла или \"exit\", чтобы выйти из программы: ");
                while (choise) {
                    String name = bufferedReader.readLine();
                    if (name.equals("exit")) System.exit(0);
                    file = new File(name);
                    try{
                        Parser Parser = new Parser();
                        Parser.parse(name);
                        choise = false;
                        path = file.getName();
                        System.out.println("Файл \"" + path +"\" был открыт");
                    } catch (RightException e1) {
                        System.out.println ("Отсутствуют права доступа к файлу, " +
                                "попробуйте изменить права доступа к файлу и ввести название файла заново " +
                                "или \"exit\", чтобы выйти из программы: ");
                    } catch (JAXBException e1) {
                        System.out.println("Данные в файле были записаны в неправильном формате," +
                                " попробуйте изменить содержание файла и ввести название файла заново " +
                                "или \"exit\", чтобы выйти из программы: ");
                    } catch (ExistanceException e1) {
                        System.out.println("Такого файла не существует, введите " +
                                "название файла заново или \"exit\", чтобы выйти из программы: ");
                    }
                }
            } else {
                System.out.println("Введите название файла или \"exit\", чтобы выйти из файла: ");
                String name = bufferedReader.readLine();
                if (name.equals("exit")) System.exit(0);
                file = new File(name);
                if (!file.exists()) file.createNewFile();
                else
                    while (file.exists()) {
                        if (!file.canRead() || !file.canWrite()) System.out.println("** Возникла ошибка с правами файла");
                        if (!file.canRead()) System.out.println("** Файл невозможно читать");
                        if (!file.canWrite()) System.out.println("** В файл невозможно писать");
                        System.out.println("Ошибка: данный файл существует. " +
                                "Введите название файла или \"exit\", чтобы выйти из файла:");
                        name = bufferedReader.readLine();
                        if (name.equals("exit")) System.exit(0);
                        file = new File(name);
                    }
                path = file.getPath();
                System.out.println("Файл \"" + path +"\" был создан");
            }
        }
        LinkedHashMap<Integer, HumanBeing> human = new LinkedHashMap<Integer, HumanBeing>();

        try{
            human = Converter.сonvertToLinkedHashMap((Parser.getHumanList()));
        } catch (NullPointerException e){}
        HumanList humanList = new HumanList();
        humanList.setCreationDate();
        humanList.setHumanBeings(Converter.convertToList(human));

        while (b || reader.ready()) {
            System.out.print("Введите команду: ");
            String command = bufferedReader.readLine();
            human = commandShell.Analyze(humanList, human, command, path, b);
            humanList.setHumanBeings(Converter.convertToList(human));
        }
    }
}