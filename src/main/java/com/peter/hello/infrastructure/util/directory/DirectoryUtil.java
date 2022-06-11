package com.peter.hello.infrastructure.util.directory;

import org.springframework.beans.factory.annotation.Value;

public class DirectoryUtil {

    @Value("${app.root-directory}")
    public static String rootDirectory;

    public static DirectoryTreeNode root;
    public static DirectoryTreeNode current;

}