package com.endpoint.directories.command;

import com.endpoint.directories.Directory;
import java.util.Arrays;

public class CommandProcessor {
    private static final String CMD_DELIMITER = " ";

    public void process(Directory root, String commandText) throws InvalidCommandException {
        if (root == null) throw new InvalidCommandException("root must be non-null.");
        if (commandText == null || commandText.isBlank()) throw new InvalidCommandException("commandText must be non-blank.");

        String[] commandParts = commandText.split(CMD_DELIMITER);
        // CREATE | MOVE | DELETE | LIST
        String action = commandParts[0];
        Command command = CommandFactory.createCommand(action.trim());
        command.process(root, Arrays.copyOfRange(commandParts, 1, commandParts.length));
    }
}
