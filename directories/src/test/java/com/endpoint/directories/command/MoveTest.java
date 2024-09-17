package com.endpoint.directories.command;

import com.endpoint.directories.Directory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class MoveTest {

    private static final Move COMMAND = new Move();

    @Test
    void process_WhenInvalidArgs_ThrowsException() {
        // null root
        assertThrows(InvalidCommandException.class,
                () -> COMMAND.process(null, "arg1", "arg2"), "Expected exception for null root.");

        // null command args
        assertThrows(InvalidCommandException.class,
                () -> COMMAND.process(new Directory(), null), "Expected exception for null commandArgs.");

        // invalid command args
        assertThrows(InvalidCommandException.class,
                () -> COMMAND.process(new Directory(), "arg1"), "Expected exception for invalid commandArgs.");
    }

    @Test
    void process_WhenValidArgs_CallsMove() throws InvalidCommandException {
        Directory directory = mock(Directory.class);
        COMMAND.process(directory, "arg1", "arg2");
        verify(directory, times(1)).move("arg1", "arg2");
    }
}
