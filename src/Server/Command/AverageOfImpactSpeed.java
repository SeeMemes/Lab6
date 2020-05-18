package Server.Command;

import Server.MyOwnClasses.HumanBeing;
import Server.MyOwnClasses.HumanList;

import java.util.LinkedHashMap;

public class AverageOfImpactSpeed extends Command {

    public AverageOfImpactSpeed(LinkedHashMap<Integer, HumanBeing> human, String comand, HumanList humanList) {
        super(human, comand, humanList);
    }

    @Override
    public LinkedHashMap<Integer, HumanBeing> execute(LinkedHashMap<Integer, HumanBeing> human, String command, HumanList humanList, String path, boolean b) {
        throw new ClassCastException();
    }

    public String execute (LinkedHashMap<Integer, HumanBeing> human, String command, HumanList humanList, String path){
        int k = 0;
        for (int i = 0; i < humanList.getHumanBeings().size(); i++)
            k += humanList.getHumanBeing(i).getImpactSpeed();
        return Integer.toString(k / humanList.getHumanBeings().size());
    }
}