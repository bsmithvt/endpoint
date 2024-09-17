package com.endpoint.directories.command;

import com.endpoint.directories.Directory;

import java.util.Arrays;

public class CommandProcessor {
    private static final String CMD_DELIMITER = " ";

    public void process(Directory root, String commandText) throws InvalidCommandException {
        if (commandText == null || commandText.length() == 0) return;

        String[] commandParts = commandText.split(CMD_DELIMITER);
        // CREATE | MOVE | DELETE | LIST
        String action = commandParts[0];
        Command command = CommandFactory.createCommand(action);
        command.process(root, Arrays.copyOfRange(commandParts, 1, commandParts.length));
    }
}
