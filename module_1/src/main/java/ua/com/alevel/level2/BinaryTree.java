package ua.com.alevel.level2;

import ua.com.alevel.UserInterface;

import java.util.Scanner;


public class BinaryTree {
    Node root;

    public static void createBinaryTree() {
        Scanner input = new Scanner(System.in);
        int center;
        int left, right;
        BinaryTree tree = new BinaryTree();
        System.out.println("Type value of root");
        while ((center = input.nextInt()) != 0) {
            tree.root = new Node(center);
            System.out.println("Attention max depth is 3");
            System.out.println("Create a node:");
            System.out.println("(1)Left node");
            System.out.println("(2)Right node");
            System.out.println("(3)Depth of tree now");
            center = input.nextInt();
            switch (center) {
                case 1 -> {
                    tree.root.left = new Node(1);
                    System.out.println("Create a node:");
                    System.out.println("(1)Left node");
                    System.out.println("(2)Right node");
                    System.out.println("(3)Depth of tree now");
                    while ((left = input.nextInt()) != 0) {
                        switch (left) {
                            case 1 -> {
                                tree.root.left.left = new Node(1);
                                System.out.println("Create a node:");
                                System.out.println("(1)Left node");
                                System.out.println("(2)Right node");
                                System.out.println("(3)Depth of tree now");
                                System.out.println("Depth of tree is : " + tree.maxDepth(tree.root));
                                UserInterface.SelectSecondLevel();
                            }
                            case 2 -> {
                                tree.root.left.right = new Node(1);
                                System.out.println("Create a node:");
                                System.out.println("(1)Left node");
                                System.out.println("(2)Right node");
                                System.out.println("(3)Depth of tree now");
                                System.out.println("Depth of tree is : " + tree.maxDepth(tree.root));
                                UserInterface.SelectSecondLevel();
                            }
                            case 3 -> {
                                System.out.println("Depth of tree is : " + tree.maxDepth(tree.root));
                                UserInterface.SelectSecondLevel();
                            }
                        }
                    }
                }
                case 2 -> {
                    tree.root.right = new Node(1);
                    System.out.println("Create a node:");
                    System.out.println("(1)Left node");
                    System.out.println("(2)Right node");
                    System.out.println("(3)Depth of tree now");
                    while ((right = input.nextInt()) != 0) {
                        switch (right) {
                            case 1 -> {
                                tree.root.right.left = new Node(1);
                                System.out.println("Create a node:");
                                System.out.println("(1)Left node");
                                System.out.println("(2)Right node");
                                System.out.println("(3)Depth of tree now");
                                System.out.println("Depth of tree is : " + tree.maxDepth(tree.root));
                                UserInterface.SelectSecondLevel();
                            }
                            case 2 -> {
                                tree.root.right.right = new Node(1);
                                System.out.println("Create a node:");
                                System.out.println("(1)Left node");
                                System.out.println("(2)Right node");
                                System.out.println("(3)Depth of tree now");
                                System.out.println("Depth of tree is : " + tree.maxDepth(tree.root));
                                UserInterface.SelectSecondLevel();
                            }
                            case 3 -> {
                                System.out.println("Depth of tree is : " + tree.maxDepth(tree.root));
                                UserInterface.SelectSecondLevel();
                            }
                        }
                    }
                }
                case 3 -> {
                    System.out.println("Depth of tree is : " + tree.maxDepth(tree.root));
                    UserInterface.SelectSecondLevel();
                }
            }
        }
    }

    int maxDepth(Node node) {
        if (node == null)
            return 0;
        else {
            int lDepth = maxDepth(node.left);
            int rDepth = maxDepth(node.right);

            if (lDepth > rDepth)
                return (lDepth + 1);
            else
                return (rDepth + 1);
        }
    }
}