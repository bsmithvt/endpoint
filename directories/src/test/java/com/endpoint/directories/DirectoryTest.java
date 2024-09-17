package com.endpoint.directories;

import com.endpoint.directories.command.InvalidCommandException;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DirectoryTest {

    @Test
    void create_WhenInvalidPath_ThrowsException() {
        Directory directory = new Directory();

        // null root
        assertThrows(IllegalArgumentException.class,
                () -> directory.create(null), "Expected exception for null path.");

        // null commandText
        assertThrows(IllegalArgumentException.class,
                () -> directory.create(""), "Expected exception for empty path.");
    }

    @Test
    void createMoveDelete_WhenValidPath_CreatesDirectory() throws InvalidCommandException {
        Directory directory = new Directory();

        // create
        directory.create("fruits/apple");

        TreeMap<String, Directory.Node> children = directory.root().children;
        assertEquals(1, children.size());

        Directory.Node fruitsNode = children.get("fruits");
        assertEquals("fruits", fruitsNode.name);
        assertEquals(directory.root().name, fruitsNode.parent.name);

        children = fruitsNode.children;
        Directory.Node appleNode = children.get("apple");
        assertEquals(1, children.size());
        assertEquals("apple", appleNode.name);
        assertEquals("fruits", appleNode.parent.name);

        // move
        directory.create("food");
        directory.move("fruits", "food");

        children = directory.root().children;
        assertEquals(1, children.size());

        Directory.Node foodNode = children.get("food");
        assertEquals("food", foodNode.name);
        assertEquals(directory.root().name, foodNode.parent.name);

        children = foodNode.children;
        fruitsNode = children.get("fruits");
        assertEquals(1, children.size());
        assertEquals("fruits", fruitsNode.name);
        assertEquals("food", fruitsNode.parent.name);

        children = fruitsNode.children;
        appleNode = children.get("apple");
        assertEquals(1, children.size());
        assertEquals("apple", appleNode.name);
        assertEquals("fruits", appleNode.parent.name);

        // delete
        directory.delete("food/fruits/apple");
        children = directory.root().children;
        assertEquals(1, children.size());

        foodNode = children.get("food");
        assertEquals("food", foodNode.name);
        assertEquals(directory.root().name, foodNode.parent.name);

        children = foodNode.children;
        fruitsNode = children.get("fruits");
        assertEquals(0, fruitsNode.children.size());
        assertEquals("fruits", fruitsNode.name);
        assertEquals("food", fruitsNode.parent.name);
    }

    @Test
    void move_WhenInvalidPath_ThrowsException() {
        Directory directory = new Directory();

        // null root
        assertThrows(IllegalArgumentException.class,
                () -> directory.move("path", null), "Expected exception for null targetPath.");

        // null commandText
        assertThrows(IllegalArgumentException.class,
                () -> directory.move("path", ""), "Expected exception for empty targetPath.");

        // null root
        assertThrows(IllegalArgumentException.class,
                () -> directory.move(null, "path"), "Expected exception for null sourcePath.");

        // null commandText
        assertThrows(IllegalArgumentException.class,
                () -> directory.move("", "path"), "Expected exception for empty sourcePath.");
    }



    @Test
    void delete_WhenInvalidPath_ThrowsException() {
        Directory directory = new Directory();

        // null root
        assertThrows(IllegalArgumentException.class,
                () -> directory.delete(null), "Expected exception for null path.");

        // null commandText
        assertThrows(IllegalArgumentException.class,
                () -> directory.delete(""), "Expected exception for empty path.");
    }
}
