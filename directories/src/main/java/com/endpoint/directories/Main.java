package com.endpoint.directories;

import com.endpoint.directories.command.CommandProcessor;
import com.endpoint.directories.command.InvalidCommandException;

import java.util.List;

public class Main {
    private static final List<String> COMMAND_LIST = List.of(
            "CREATE fruits",
            "CREATE vegetables",
            "CREATE grains",
            "CREATE fruits/apples",
            "CREATE fruits/apples/fuji",
            "LIST",
            "CREATE grains/squash",
            "MOVE grains/squash vegetables",
            "CREATE foods",
            "MOVE grains foods",
            "MOVE fruits foods",
            "MOVE vegetables foods",
            "LIST",
            "DELETE fruits/apples",
            "DELETE foods/fruits/apples",
            "LIST"
    );

    public static void main(String[] args) {
        var commandProcessor = new CommandProcessor();
        Directory root = new Directory();
        for (String command : COMMAND_LIST) {
            try {
                System.out.println(command);
                commandProcessor.process(root, command);
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}