package com.company;

class LeafyTree extends Tree{

    private int number_of_leafs;

    LeafyTree(int thickness_trunk, int number_of_branches, int length_of_branches, int age_of_tree, int number_of_leafs) {
        super(thickness_trunk, number_of_branches, length_of_branches, age_of_tree);
        this.number_of_leafs = number_of_leafs;
    }

    @Override
    void growTree() {
        System.out.println("Wypuszczam młode liście...");
    }
}
