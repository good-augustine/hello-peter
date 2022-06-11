package com.peter.hello.infrastructure.util.directory;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;

public class FileVisitorImpl implements FileVisitor<Path> {

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
        DirectoryTreeNode node = new DirectoryTreeNode(dir, dir.getFileName().toString(), "directory", attrs.size(), new HashSet<>());
        if (DirectoryUtil.current == null) {
            DirectoryUtil.root = DirectoryUtil.current = node;
        } else {
            node.setParent(DirectoryUtil.current);
            DirectoryUtil.current.getChildren().add(node);
            DirectoryUtil.current = node;
        }

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        DirectoryTreeNode node = new DirectoryTreeNode(
                file, file.getFileName().toString(), attrs.isDirectory() ? "directory" : "file", attrs.size(), null
        );
        node.setParent(DirectoryUtil.current);
        DirectoryUtil.current.getChildren().add(node);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
        DirectoryUtil.current = DirectoryUtil.current.getParent();
        return FileVisitResult.CONTINUE;
    }
}
