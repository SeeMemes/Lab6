package Server.Command;

import Server.MyOwnClasses.HumanBeing;
import Server.MyOwnClasses.HumanList;

import java.util.LinkedHashMap;

public class Show extends Command {
    public Show(LinkedHashMap<Integer, HumanBeing> human, String comand, HumanList humanList) {
        super(human, comand, humanList);
    }

    @Override
    public LinkedHashMap<Integer, HumanBeing> execute(LinkedHashMap<Integer, HumanBeing> human, String command, HumanList humanList, String path, boolean b) {
        throw new ClassCastException();
    }

    public String execute (LinkedHashMap<Integer, HumanBeing> human, String command, HumanList humanList, String path){
        String answer = "Коллекция: \n" + humanList.getHumanBeings().toString();
        return answer;
    }
}
