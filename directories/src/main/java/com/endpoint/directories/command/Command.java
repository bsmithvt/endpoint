package com.endpoint.directories.command;

import com.endpoint.directories.Directory;

public interface Command {
    void process(Directory root, String... commandArgs) throws InvalidCommandException;
}
