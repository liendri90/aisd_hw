package task3;

import java.util.List;
import java.util.ArrayList;

public class NTreeSerializer {
    private static class NTreeNode {
        int val;
        List<NTreeNode> children;

        NTreeNode(int val) {
            this.val = val;
            this.children = new ArrayList<>();
        }
    }

    public String serialize(NTreeNode root) {
        if (root == null) {
            return "NULL";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("(").append(root.val);

        if (!root.children.isEmpty()) {
            sb.append(", ");
            for (int i = 0; i < root.children.size(); i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(serialize(root.children.get(i)));
            }
        } else {
            sb.append(", NULL");
        }

        sb.append(")");
        return sb.toString();
    }

    public static void main(String[] args) {
        NTreeNode root = new NTreeNode(1);
        root.children.add(new NTreeNode(2));

        NTreeNode node3 = new NTreeNode(3);
        node3.children.add(new NTreeNode(5));
        node3.children.add(new NTreeNode(6));
        root.children.add(node3);

        root.children.add(new NTreeNode(4));

        NTreeSerializer serializer = new NTreeSerializer();
        System.out.println(serializer.serialize(root));
    }
}
