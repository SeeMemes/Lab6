package Server; /**
 * This class allows us to translate String commands to methods which we can execute
 *
 * @exception java.lang.StringIndexOutOfBoundsException can be caused if we will crop too short string
 */

import Server.Command.*;
import Server.Exceptions.HumanValueException;
import Server.MyOwnClasses.HumanBeing;
import Server.MyOwnClasses.HumanList;

import java.util.*;

public class CommandShell {
    public CommandShell() {}
    public static LinkedHashMap<Integer, HumanBeing> Analyze(HumanList humanList, LinkedHashMap<Integer, HumanBeing> human, String command, String path, boolean b) {
        HashMap<String, Command> commandList = new LinkedHashMap<>();
        StringTokenizer tokenizer = new StringTokenizer(command);
        commandList.put("save", new Save(human, command, humanList));
        commandList.put("info", new Info(human, command, humanList));
        commandList.put("exit", new Exit(human, command, humanList));
        commandList.put("help", new Help(human, command, humanList));
        commandList.put("show", new Show(human, command, humanList));
        commandList.put("clear", new Clear(human, command, humanList));
        commandList.put("update", new Update(human, command, humanList));
        commandList.put("insert", new Insert(human, command, humanList));
        commandList.put("remove_key", new RemoveKey(human, command, humanList));
        commandList.put("execute_script", new ExecuteScript(human, command, humanList));
        commandList.put("replace_if_lowe", new ReplaceIfLowe(human, command, humanList));
        commandList.put("remove_lower_key", new RemoveLowerKey(human, command, humanList));
        commandList.put("remove_greater_key", new RemoveGreaterKey(human, command, humanList));
        commandList.put("sum_of_impact_speed", new SumOfImpactSpeed(human, command, humanList));
        commandList.put("average_of_impact_speed", new AverageOfImpactSpeed(human, command, humanList));
        commandList.put("print_field_descending_mood", new PrintFieldDescendingMood(human, command, humanList));
        try {
            String token = tokenizer.nextToken();
            human = commandList.get(token.toLowerCase()).execute(human, command, humanList, path, b);
        } catch (StringIndexOutOfBoundsException e) { System.out.println("Команда была введена неверно"); }
        catch (ArithmeticException e) { System.out.println("Лист HumanList пуст"); }
        catch (NullPointerException e) { System.out.println("Команда была введена неверно"); }
        catch (HumanValueException e) { System.out.println("Введите правильный тип данных"); }
        return human;
    }
}
