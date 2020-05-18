package Server.Command;

import Server.MyOwnClasses.HumanBeing;
import Server.MyOwnClasses.HumanList;

import java.util.*;

public class RemoveGreaterKey extends Command {
    public RemoveGreaterKey(LinkedHashMap<Integer, HumanBeing> human, String comand, HumanList humanList) {
        super(human, comand, humanList);
    }

    public LinkedHashMap<Integer, HumanBeing> execute (LinkedHashMap<Integer, HumanBeing> human, String command, HumanList humanList, String path, boolean b) {
        Set<Integer> keys = human.keySet();
        List<Integer> listKeys = new ArrayList<Integer>(keys);
        StringTokenizer stringTokenizer = new StringTokenizer(command);
        stringTokenizer.nextToken();
        for (int i = 0; i < listKeys.size(); i++)
            if (listKeys.get(i) > Integer.parseInt(stringTokenizer.nextToken()))
                human.remove(listKeys.get(i));
        return human;
    }
}
