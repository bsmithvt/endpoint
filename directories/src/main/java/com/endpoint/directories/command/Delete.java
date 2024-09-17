package com.endpoint.directories.command;

import com.endpoint.directories.Directory;

class Delete implements Command {
    private static final int ARG_LENGTH = 1;

    @Override
    public void process(Directory root, String... commandArgs) throws InvalidCommandException {
        if (commandArgs == null || commandArgs.length != ARG_LENGTH) {
            throw new InvalidCommandException(String.format("DELETE command requires %d argument.", ARG_LENGTH));
        }

        root.delete(commandArgs[0].trim());
    }
}
