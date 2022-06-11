package com.peter.hello.application.controller;

import com.peter.hello.infrastructure.util.directory.DirectoryTreeNode;
import com.peter.hello.infrastructure.util.directory.DirectoryUtil;
import com.peter.hello.infrastructure.util.directory.FileVisitorImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FileController {

    //@Value("${app.root-directory}")
    //private String rootDirectory;

    @GetMapping(value = "/files")
    public ResponseEntity<DirectoryTreeNode> getAllFiles() throws IOException {
        log.debug("getAllFiles called...");
        long start = System.currentTimeMillis();
        Files.walkFileTree(Paths.get(DirectoryUtil.rootDirectory), new HashSet<>(Collections.singleton(FileVisitOption.FOLLOW_LINKS)), 1, new FileVisitorImpl());
        long finish = System.currentTimeMillis();
        log.debug("Elapsed Time: " + (finish - start));
        return ResponseEntity.ok(DirectoryUtil.root);
    }

}
