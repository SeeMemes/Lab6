/**
 * This class allows us to translate String commands to methods which we can execute
 *
 * @exception java.lang.StringIndexOutOfBoundsException can be caused if we will crop too short string
 */

/*package Server.MyOwnClasses;

import Server.Tools.CompareHumanBeings;
import Server.Tools.Converter;
import Server.enums.Mood;

import java.util.*;

public class OldCommandShell {
    public static LinkedHashMap<Integer, HumanBeing> Analyze(HumanList humanList, LinkedHashMap<Integer, HumanBeing> human, String comand, String path) {
        try {
            if (comand.substring(0, 4).equals("help")) Commands.help();

            else if (comand.substring(0, 4).equals("save")) {
                humanList.setHumanBeings(Converter.convertToList(human));
                Commands.save(humanList, path);
            } else if (comand.substring(0, 4).equals("exit")) System.exit(1);

            else if (comand.substring(0, 4).equals("show")) Commands.show(human);

            else if (comand.substring(0, 5).equals("clean")) human = Commands.clean();

            else if (comand.substring(0, 6).equals("update")) {
                humanList.setHumanBeings(Converter.convertToList(human));
                try {
                    Commands.update(humanList, Integer.parseInt(comand.substring(7, comand.length())));
                    Converter.сonvertToLinkedHashMap(humanList);
                } catch (InputMismatchException e) {
                    System.out.println("Попробуйте еще раз, введя правильные данные.");
                }
            } else if (comand.substring(0, 6).equals("insert")) {
                HumanBeing humanBeing = new HumanBeing();
                Commands.setHumanBeing(humanBeing, humanList.getHumanBeings().size() + 1);
                human.put(Integer.parseInt(comand.substring(7, comand.length())), humanBeing);
                humanList.setHumanBeings(Converter.convertToList(human));
            } else if (comand.substring(0, 10).equals("remove_key")) {
                human.remove(Integer.parseInt(comand.substring(11, comand.length())));
                humanList.setHumanBeings(Converter.convertToList(human));
            } else if (comand.substring(0, 14).equals("execute_script")) {
                human = Commands.ReadScript(comand.substring(15, comand.length()), humanList, human, path);
                humanList.setHumanBeings(Converter.convertToList(human));
            } else if (comand.substring(0, 15).equals("replace_if_lowe")) {
                HumanBeing humanBeing = new HumanBeing();
                int key = Integer.parseInt(comand.substring(16, comand.length()));
                try {
                    Commands.setHumanBeing(humanBeing, humanList.getHumanBeings().size() + 1);
                } catch (InputMismatchException e) {
                    System.out.println("Попробуйте еще раз, введя правильные данные.");
                }
                if (CompareHumanBeings.compare(humanBeing, human.get(key))) {
                    human.replace(key, human.get(key), humanBeing);
                    humanList.setHumanBeings(Converter.convertToList(human));
                }
            } else if (comand.substring(0, 16).equals("remove_lower_key")) {
                Set<Integer> keys = human.keySet();
                List<Integer> listKeys = new ArrayList<Integer>(keys);
                for (int i = 0; i < listKeys.size(); i++)
                    if (listKeys.get(i) < Integer.parseInt(comand.substring(17, comand.length())))
                        human.remove(listKeys.get(i));
            } else if (comand.substring(0, 18).equals("remove_greater_key")) {
                Set<Integer> keys = human.keySet();
                List<Integer> listKeys = new ArrayList<Integer>(keys);
                for (int i = 0; i < listKeys.size(); i++)
                    if (listKeys.get(i) > Integer.parseInt(comand.substring(19, comand.length())))
                        human.remove(listKeys.get(i));
            } else if (comand.substring(0, 19).equals("sum_of_impact_speed")) {
                int k = 0;
                for (int i = 0; i < humanList.getHumanBeings().size(); i++)
                    k += humanList.getHumanBeing(i).getImpactSpeed();
                System.out.println(k);
            } else if (comand.substring(0, 23).equals("average_of_impact_speed")) {
                int k = 0;
                for (int i = 0; i < humanList.getHumanBeings().size(); i++)
                    k += humanList.getHumanBeing(i).getImpactSpeed();
                System.out.println(k / humanList.getHumanBeings().size());
            } else if (comand.substring(0, 27).equals("print_field_descending_mood")) {
                Set<Mood> set = new TreeSet<>(Comparator.comparing(Mood::toString));
                for (int i = 0; i < humanList.getHumanBeings().size(); i++)
                    set.add(humanList.getHumanBeing(i).getMood());
                System.out.println(set);
            } else System.out.println("Команда была введена неверно");

        } catch (StringIndexOutOfBoundsException e) {System.out.println("Команда была введена неверно");}
        catch (ArithmeticException e) {System.out.println("Лист HumanList пуст");}
        return human;
    }
}*/
