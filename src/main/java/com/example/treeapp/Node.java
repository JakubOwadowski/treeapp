package com.example.treeapp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Node {
    @Id @Column(name = "id", nullable = false) private int id;
    private int parent_id;
    private int value;

    Node() {}

    Node(int id, int parent_id, int value) {
        this.id = id;
        this.parent_id = parent_id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Node))
            return false;
        Node node = (Node) o;
        return Objects.equals(this.id, node.id)
                && Objects.equals(this.parent_id, node.parent_id)
                && Objects.equals(this.value, node.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.parent_id, this.value);
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + this.id +
                ", parent_id=" + this.parent_id +
                ", value=" + this.value + "}";
    }
}
