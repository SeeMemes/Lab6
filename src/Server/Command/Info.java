package Server.Command;

import Server.MyOwnClasses.HumanBeing;
import Server.MyOwnClasses.HumanList;

import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;

public class Info extends Command {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Info(LinkedHashMap<Integer, HumanBeing> human, String comand, HumanList humanList) {
        super(human, comand, humanList);
    }

    @Override
    public LinkedHashMap<Integer, HumanBeing> execute(LinkedHashMap<Integer, HumanBeing> human, String command, HumanList humanList, String path, boolean b) {
        throw new ClassCastException();
    }

    public String execute (LinkedHashMap<Integer, HumanBeing> human, String command, HumanList humanList, String path) {
        String answer = "Type: LinkedHashMap\n" + "Initialization date: " + humanList.getCreationDate().format(formatter) + "\n"
                + "Number of elements: " + humanList.getHumanBeings().size();
        return answer;
    }
}
