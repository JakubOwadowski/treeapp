package com.example.treeapp;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TreeRepository extends JpaRepository<Node, Integer> {
}
