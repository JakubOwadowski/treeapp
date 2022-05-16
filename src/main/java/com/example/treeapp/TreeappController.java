package com.example.treeapp;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TreeappController {

    private final TreeRepository repository;
    private Tree tree;

    public TreeappController(TreeRepository treeRepository) {
        this.repository = treeRepository;
    }

    @GetMapping("/tree")
    List<Node> all() {
        List<Node> nodes = repository.findAll();
        for (Node node : nodes) {
            if (node.getParent_id() == -1) tree = new Tree(null, node.getValue());
            else tree.getNodeByID(node.getParent_id()).addChild(node.getId(), node.getValue());
        }
        return tree.getNodes();
    }
}
