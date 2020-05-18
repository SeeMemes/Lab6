package Client;

import java.io.Serializable;

public class ServerRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    String command;
    String arguments;

    public ServerRequest(String command, String arguments){
        this.command = command;
        this.arguments = arguments;
    }

    public String getCommand() {
        return command;
    }

    public String getArguments() {
        return arguments;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        return "Request{" +
                "command='" + command + '\'' +
                ", arguments='" + arguments + '\'' +
                '}';
    }
}
