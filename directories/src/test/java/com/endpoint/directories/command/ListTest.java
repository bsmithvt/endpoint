package com.endpoint.directories.command;

import com.endpoint.directories.Directory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ListTest {

    private static final List COMMAND = new List();

    @Test
    void process_WhenInvalidArgs_ThrowsException() {
        // null root
        assertThrows(InvalidCommandException.class,
                () -> COMMAND.process(null), "Expected exception for null root.");

        // invalid command args
        assertThrows(InvalidCommandException.class,
                () -> COMMAND.process(new Directory(), "arg1"), "Expected exception for invalid commandArgs.");
    }

    @Test
    void process_WhenValidArgs_CallsList() throws InvalidCommandException {
        Directory directory = mock(Directory.class);
        COMMAND.process(directory, null);
        verify(directory, times(1)).list();

        reset(directory);
        COMMAND.process(directory);
        verify(directory, times(1)).list();
    }
}
