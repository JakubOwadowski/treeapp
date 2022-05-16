package com.example.treeapp;
import java.util.ArrayList;
import java.util.List;

import com.example.treeapp.Node;

/*
 * A simple Java Tree
 * Includes all CRUD operations.
 * @author Jakub Owadowski
 * */

public class Tree {
    private ArrayList<Tree> children;
    private Tree parent;
    private int value;
    private final int id;
    private static int max_int = 0;
    private ArrayList<Node> nodes;

    /*
     * Constructor
     * @param parent - parent of the created node, usually leave as 'null'.
     * @param value - integer with the node value.
     * */
    public Tree(Tree parent, int value) {
        this.parent = parent;
        this.value = value;
        this.children = new ArrayList<>();
        if (parent == null) max_int = 0;
        this.id = max_int;
        max_int++;
    }

    public Tree(Tree parent, int id, int value) {
        this.parent = parent;
        this.value = value;
        this.children = new ArrayList<>();
        if (parent == null) max_int = 0;
        this.id = id;
        if (id > max_int) max_int = id + 1;
    }

    /*
     * Adds all the values from the node up
     * @return result - value of the branch
     * */
    public int addValues() {
        if (parent != null) {
            int result = parent.addValues();
            result += this.value;
            return result;
        } else {
            return this.value;
        }
    }

    /*
     * Deletes the current node and all its children
     * @exception You cannot delete the root, for this you need to manually reassign it as a new Tree.
     * */
    public void delete() throws Exception {
        if (this.parent != null) {
            deleteFun();
            this.parent.children.remove(this);
        } else {
            throw new Exception("Can't delete the root!");
        }
    }

    /*
     * A private function that aids in the deletion
     * Clears the children ArrayList.
     * */
    private void deleteFun() {
        for (Tree child : this.children) {
            try {
                child.deleteFun();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.children = new ArrayList<>();
    }

    /*
     * Adds a child to the current node.
     * @param value - integer with the node value.
     * */
    public void addChild(int value) {
        this.children.add(new Tree(this, value));
    }

    public void addChild(int id, int value) {
        this.children.add(new Tree(this, id, value));
    }

    /*
     * Adds a copy of a child to the destination node
     * Warning - the nodes cannot be parents of the node!
     * @param id_source - node id to copy.
     * @param id_destination - node id to add the new child to.
     * */
    public void addCopyChild(int id_source, int id_destination) {
        this.getNodeByID(id_destination).addChild(this.getNodeByID(id_source).getValue());
    }

    /*
     * Moves the source node and all of its children to the destination
     * Warning - the nodes cannot be parents of the node!
     * @param id_source - node id to move.
     * @param id_destination - node id to move to.
     * */
    public void move(int id_source, int id_destination) {
        Tree source = this.getNodeByID(id_source);
        Tree destination = this.getNodeByID(id_destination);
        Tree parent = source.parent;
        parent.children.remove(source);
        source.parent = destination;
        destination.children.add(source);
    }

    /*
     * Value getter
     * @return value - the value of the node
     * */
    public int getValue() {
        return this.value;
    }

    /*
     * Value setter
     * @param value - the new value to set
     * */
    public void setValue(int value) {
        this.value = value;
    }

    /*
     * Gets a child of the node at the index.
     * Children are indexed from 0 to n-1 where n is the # of children.
     * @param index - index of the child
     * @return child - the found child
     * */
    public Tree getChild(int index) {
        return this.children.get(index);
    }

    /*
     * Gets the parent of the node.
     * @return parent - the parent node. Can be null.
     * */
    public Tree getParent() {
        return this.parent;
    }

    /*
     * Gets an ArrayList of children.
     * @return children - the children.
     * */
    public ArrayList<Tree> getChildren() {
        return this.children;
    }

    /*
     * Gets the ID of the node.
     * @return id - the ID of the node.
     * */
    public int getID() {
        return this.id;
    }

    /*
     * Gets a node with the requested id.
     * Warning - the node cannot be a parent of the node!
     * If node isn't found, will return null.
     * @param id - the id of the node to find
     * @return result - the found node.
     * */
    public Tree getNodeByID(int id) {
        if (this.id == id) {
            return this;
        } else {
            for (Tree child : this.children) {
                Tree result = child.getNodeByID(id);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }

    /*
    * Returns an array of nodes for simple storage in a db
    * @return nodes - a list of Node objects
    * */
    public ArrayList<Node> getNodes() {
        nodes = new ArrayList<Node>();
        getNodesFunc(this);
        return nodes;
    }

    /*
    * An auxiliary function for getting nodes
    * @param tree - the tree to search in
    * */
    private void getNodesFunc(Tree tree) {
        int parentID;
        if (tree.getParent() == null) {
            parentID = -1;
        } else {
            parentID = tree.getParent().getID();
        }
        nodes.add(new Node(tree.id, parentID, tree.value));
        for (Tree child : tree.getChildren()) {
            getNodesFunc(child);
        }
    }
}