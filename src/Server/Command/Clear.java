package Server.Command;

import Server.MyOwnClasses.HumanBeing;
import Server.MyOwnClasses.HumanList;

import java.util.LinkedHashMap;

public class Clear extends Command {
    public Clear(LinkedHashMap<Integer, HumanBeing> human, String comand, HumanList humanList) {
        super(human, comand, humanList);
    }

    public LinkedHashMap<Integer, HumanBeing> execute (LinkedHashMap<Integer, HumanBeing> human, String command, HumanList humanList, String path, boolean b){
        LinkedHashMap<Integer, HumanBeing> linkedHashMap = new LinkedHashMap<Integer, HumanBeing>();
        System.out.println("Очистка произведена");
        return linkedHashMap;
    }
}
