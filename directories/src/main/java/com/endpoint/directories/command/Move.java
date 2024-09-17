package com.endpoint.directories.command;

import com.endpoint.directories.Directory;

class Move implements Command {
    private static final int ARG_LENGTH = 2;

    @Override
    public void process(Directory root, String... commandArgs) throws InvalidCommandException {
        if (commandArgs == null || commandArgs.length != ARG_LENGTH) {
            throw new InvalidCommandException(String.format("MOVE command requires %d arguments.", ARG_LENGTH));
        }

        root.move(commandArgs[0].trim(), commandArgs[1].trim());
    }
}
