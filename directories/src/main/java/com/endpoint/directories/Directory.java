package com.endpoint.directories;

import com.endpoint.directories.command.InvalidCommandException;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public class Directory {
    private static final String PATH_DELIMITER = "/";

    private Node root = new Node("ROOT_" + UUID.randomUUID(), null);

    public void create(String path) {
        String[] pathParts = path.split(PATH_DELIMITER);
        Node node = root;
        for (String part : pathParts) {
             Node child = node.children.get(part);
             if (child == null) {
                 child = new Node(part, node);
                 node.children.put(part, child);
                 break;
             }

             node = child;
        }
    }

    public void move(String sourcePath, String targetPath) throws InvalidCommandException {
        // navigate to source node
        Node sourceNode = root;
        String[] sourcePathParts = sourcePath.split(PATH_DELIMITER);
        for (var i=0; i < sourcePathParts.length; i++) {
            String sourcePathPart = sourcePathParts[i];
            sourceNode = sourceNode.children.get(sourcePathPart);
            if (i == sourcePathParts.length-1) {
                break;
            }
            if (sourceNode == null) {
                throw new InvalidCommandException(String.format(
                        "Cannot move %s to %s - %s in source path does not exist.",
                        sourcePath,
                        targetPath,
                        sourcePathPart));
            }
        }

        // navigate to target node
        Node targetNode = root;
        String[] targetPathParts = targetPath.split(PATH_DELIMITER);
        for (var i=0; i < targetPathParts.length; i++) {
            String targetPathPart = targetPathParts[i];
            targetNode = targetNode.children.get(targetPathPart);
            if (i == targetPathParts.length - 1) {
                break;
            }
            if (targetNode == null) {
                throw new InvalidCommandException(String.format(
                        "Cannot move %s to %s - %s in target path does not exist.",
                        sourcePath,
                        targetPath,
                        targetPathPart));
            }
        }

        var newNode = new Node(sourceNode.name, targetNode);
        newNode.children.putAll(Map.copyOf(sourceNode.children));
        targetNode.children.put(newNode.name, newNode);
        sourceNode.parent.children.remove(sourceNode.name);
    }

    public void delete(String path) throws InvalidCommandException {
        String[] pathParts = path.split(PATH_DELIMITER);
        Node node = root;
        for (var i=0; i < pathParts.length; i++) {
            String part = pathParts[i];
            Node child = node.children.get(part);
            if (child == null) {
                throw new InvalidCommandException(String.format("Cannot delete %s - %s does not exist", path, part));
            }
            // if we're on the last segment of path, delete it
            if (i == pathParts.length-1) {
                node.children.remove(part);
                break;
            }
            node = child;
        }
    }

    public void list() {
        print(root, -1);
    }

    private void print(Node node, int level) {
        if (level > -1) { // root does not have a name, so skip it
            // print 3 spaces per level (i.e. 3 spaces for level 1, 6 for level 2, etc.)
            for (var i = 0; i < level * 3; i++) {
                System.out.print(" ");
            }
            System.out.println(node.name);
        }
        // print the children for this node
        for (Map.Entry<String, Node> child : node.children.entrySet()) {
            print(child.getValue(), level + 1);
        }
    }

    private static class Node {
        private String name;
        private Node parent;
        private TreeMap<String, Node> children = new TreeMap<>();

        Node(String name, Node parent) {
            this.name = name;
            this.parent = parent;
        }
    }
}
