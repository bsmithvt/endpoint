package com.endpoint.directories.command;

public class CommandFactory {
    private static final String CREATE = "create";
    private static final String MOVE = "move";
    private static final String DELETE = "delete";
    private static final String LIST = "list";

    public static Command createCommand(String command) {
        if (command == null) throw new NullPointerException("command cannot be null.");
        return switch (command.toLowerCase()) {
            case CREATE -> new Create();
            case MOVE -> new Move();
            case DELETE -> new Delete();
            case LIST -> new List();
            default -> throw new IllegalStateException("Unexpected value: " + command.toLowerCase());
        };
    }
}
