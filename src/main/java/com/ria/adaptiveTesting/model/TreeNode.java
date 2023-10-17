package com.ria.adaptiveTesting.model;

public class TreeNode {
    private String label;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(String label, TreeNode left, TreeNode right) {
        this.label = label;
        this.left = left;
        this.right = right;
    }

    public String getLabel() {
        return label;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }
}

