package com.endpoint.directories.command;

import com.endpoint.directories.Directory;

class List implements Command {
    private static final int ARG_LENGTH = 0;

    @Override
    public void process(Directory root, String... commandArgs) throws InvalidCommandException {
        if (commandArgs != null && commandArgs.length != ARG_LENGTH) {
            throw new InvalidCommandException(String.format("LIST command requires %d arguments.", ARG_LENGTH));
        }
        root.list();
    }
}
