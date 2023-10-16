package com.company;

public class Main {

    public static void main(String[] args) {
	Tree tree = new LeafyTree(1,2,3,4,5);
	ConiferousTree coniferousTree = new ConiferousTree(1,5,3,2,1);
	tree.growTree();
	coniferousTree.growTree();
        System.out.println("*******");
    }
}
