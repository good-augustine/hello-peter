package com.peter.hello.infrastructure.util.directory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.nio.file.Path;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class DirectoryTreeNode {
    private Path path;
    private String name;
    private String type;
    private long size;

    @JsonIgnore
    private DirectoryTreeNode parent;
    private Set<DirectoryTreeNode> children;

    public DirectoryTreeNode(Path path, String name, String type, long size, Set<DirectoryTreeNode> children) {
        this.path = path;
        this.name = name;
        this.type = type;
        this.size = size;
        this.children = children;
    }

}