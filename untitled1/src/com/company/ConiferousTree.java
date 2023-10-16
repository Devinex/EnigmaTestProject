package com.company;

class ConiferousTree extends Tree {

    int number_needles;

    ConiferousTree(int thickness_trunk, int number_of_branches, int length_of_branches, int age_of_tree, int number_needles) {
        super(thickness_trunk, number_of_branches, length_of_branches, age_of_tree);
        this.number_needles = number_needles;
    }


    @Override
    void growTree() {
        System.out.println("Wypuszczam młode igły...");
    }
}

