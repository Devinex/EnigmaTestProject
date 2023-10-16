package com.company;

abstract class Tree {

    private int thickness_trunk;
    private int number_of_branches;
    private int length_of_branches;
    private int age_of_tree;


    public Tree(int thickness_trunk, int number_of_branches, int length_of_branches, int age_of_tree) {
        this.thickness_trunk = thickness_trunk;
        this.number_of_branches = number_of_branches;
        this.length_of_branches = length_of_branches;
        this.age_of_tree = age_of_tree;
    }

    abstract void growTree();
}
