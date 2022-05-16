package com.example.treeapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(TreeRepository repository) {
        Tree tree = new Tree(null,1);
        tree.addChild(2);
        tree.addChild(3);
        tree.addCopyChild(1, 0);
        tree.getNodeByID(2).addChild(5);
        tree.getNodeByID(2).addChild(6);
        tree.getNodeByID(5).addChild(10);
        ArrayList<Node> nodes = tree.getNodes();
        return args -> {
            for (Node node : nodes) {
                log.info("Preloading " + repository.save(new Node(node.getId(), node.getParent_id(), node.getValue())));
            }
        };
    }
}
