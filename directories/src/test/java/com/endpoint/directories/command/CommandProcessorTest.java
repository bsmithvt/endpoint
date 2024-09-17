package com.endpoint.directories.command;

import com.endpoint.directories.Directory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandProcessorTest {
    private static final CommandProcessor CMD_PROCESSOR = new CommandProcessor();

    @Test
    void process_WhenInvalidArgs_ThrowsException() {
        // null root
        assertThrows(InvalidCommandException.class,
                () -> CMD_PROCESSOR.process(null, "arg1"), "Expected exception for null root.");

        // null commandText
        assertThrows(InvalidCommandException.class,
                () -> CMD_PROCESSOR.process(new Directory(), null), "Expected exception for null commandText.");

        // invalid commandText
        assertThrows(InvalidCommandException.class,
                () -> CMD_PROCESSOR.process(new Directory(), " "), "Expected exception for invalid commandText.");

        // invalid operation
        assertThrows(IllegalStateException.class,
                () -> CMD_PROCESSOR.process(new Directory(), "INVALID_OP path"), "Expected exception for invalid operation.");
    }
}
