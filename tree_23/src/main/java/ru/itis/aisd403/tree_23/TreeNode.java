package ru.itis.aisd403.tree_23;

public class TreeNode {
    public Integer key1;
    public Integer key2;
    public TreeNode left;
    public TreeNode middle;
    public TreeNode right;
    public boolean isTwoNode;

    public TreeNode(int key) {
        this.key1 = key;
        this.isTwoNode = true;
        this.left = null;
        this.middle = null;
        this.right = null;
    }

    public TreeNode(int key1, int key2) {
        this.key1 = Math.min(key1, key2);
        this.key2 = Math.max(key1, key2);
        this.isTwoNode = false;
        this.left = null;
        this.middle = null;
        this.right = null;
    }

    public TreeNode(int key, TreeNode left, TreeNode right) {
        this.key1 = key;
        this.left = left;
        this.right = right;
        this.isTwoNode = true;
        this.middle = null;
    }

    public boolean isLeaf() {
        return left == null && middle == null && right == null;
    }

    @Override
    public String toString() {
        return isTwoNode ? "[" + key1 + "]" : "[" + key1 + "," + key2 + "]";
    }
}