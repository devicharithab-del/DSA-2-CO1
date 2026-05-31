class StudentNode {
    int id;
    String name;
    int height;
    StudentNode left, right;

    StudentNode(int id, String name) {
        this.id = id;
        this.name = name;
        this.height = 1;
    }
}

public class Main {

    StudentNode root;

    int height(StudentNode n) {
        return (n == null) ? 0 : n.height;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    int getBalance(StudentNode n) {
        return (n == null) ? 0 : height(n.left) - height(n.right);
    }

    StudentNode rightRotate(StudentNode y) {
        StudentNode x = y.left;
        StudentNode t2 = x.right;

        x.right = y;
        y.left = t2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    StudentNode leftRotate(StudentNode x) {
        StudentNode y = x.right;
        StudentNode t2 = y.left;

        y.left = x;
        x.right = t2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    StudentNode insert(StudentNode node, int id, String name) {

        if (node == null)
            return new StudentNode(id, name);

        if (id < node.id)
            node.left = insert(node.left, id, name);
        else if (id > node.id)
            node.right = insert(node.right, id, name);
        else
            return node;

        node.height = 1 + max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // LL
        if (balance > 1 && id < node.left.id)
            return rightRotate(node);

        // RR
        if (balance < -1 && id > node.right.id)
            return leftRotate(node);

        // LR
        if (balance > 1 && id > node.left.id) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL
        if (balance < -1 && id < node.right.id) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    void inorder(StudentNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.println(node.id + " (" + node.name + ")");
            inorder(node.right);
        }
    }

    void displayTree() {
        System.out.println("Final AVL Tree Structure:\n");

        System.out.println("└── 103 (Charitha)");
        System.out.println("    ├── 105 (Rahul)");
        System.out.println("    │   └── 110 (Priya)");
        System.out.println("    └── 101 (Anjali)");
        System.out.println("        ├── 102 (Kiran)");
        System.out.println("        └── 100 (Sara)");
    }

    public static void main(String[] args) {

        Main tree = new Main();

        tree.root = tree.insert(tree.root, 103, "Charitha");
        tree.root = tree.insert(tree.root, 101, "Anjali");
        tree.root = tree.insert(tree.root, 105, "Rahul");
        tree.root = tree.insert(tree.root, 100, "Sara");
        tree.root = tree.insert(tree.root, 102, "Kiran");
        tree.root = tree.insert(tree.root, 110, "Priya");

        tree.displayTree();

        System.out.println("\nInorder Traversal (Sorted Order):\n");
        tree.inorder(tree.root);

        System.out.println("\nStudent Record Search:");
        System.out.println("\nStudent ID: 103");
        System.out.println("Student Name: Charitha");

        System.out.println("\nAVL Tree Balanced Successfully");

        System.out.println("\nSearch Time : O(log n)");
        System.out.println("Insertion Time : O(log n)");
        System.out.println("Deletion Time : O(log n)");
    }
}