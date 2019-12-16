import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

class BinaryTree {

   public static String Result;
    static Node mainNode;

    public static void main(String[] args) {
        Result = "AAAAAAAAAAAAAAAAA";
        mainNode = new Node();
        File f = new File(args[0]);
        try {
            Scanner sc = new Scanner(f, "UTF-8");
            while (sc.hasNextLine()) {
                String tree = sc.nextLine().replaceAll(" ", "");
                if (tree.length() == 1)
                    mainNode.name = tree.toCharArray()[0];

                Node tmpNode = mainNode;
                for (int i = 1; i < tree.length(); i++) {
                    if (i == tree.length() - 1) {
                        if (tree.toCharArray()[i] == 'L') {
                            if (tmpNode.leftChild == null) {
                                Node newNode = new Node(tree.toCharArray()[0]);
                                tmpNode.leftChild = newNode;

                            } else {
                                tmpNode.leftChild.name = tree.toCharArray()[0];
                            }
                            tmpNode = tmpNode.leftChild;
                        } else if (tree.toCharArray()[i] == 'R') {
                            if (tmpNode.rightChild == null) {
                                Node newNode = new Node(tree.toCharArray()[0]);
                                tmpNode.rightChild = newNode;
                            } else {
                                tmpNode.rightChild.name = tree.toCharArray()[0];
                            }
                            tmpNode = tmpNode.rightChild;
                        } else {
                            System.out.println("Exeption");
                        }
                    } else {
                        if (tree.toCharArray()[i] == 'L') {
                            if (tmpNode.leftChild == null) {
                                Node newNode = new Node();
                                tmpNode.leftChild = newNode;
                            }
                            tmpNode = tmpNode.leftChild;
                        } else if (tree.toCharArray()[i] == 'R') {
                            if (tmpNode.rightChild == null) {
                                Node newNode = new Node();
                                tmpNode.rightChild = newNode;
                            }
                            tmpNode = tmpNode.rightChild;
                        } else {
                            System.out.println("Exeption: " + tree.toCharArray()[i]);
                            System.out.println("in line: " + tree);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        printPaths(mainNode);
        System.out.println(Result);
    }

    static void printPaths(Node node) {
        char path[] = new char[1000];
        printPathsRecur(node, path, 0);
    }

    static void printPathsRecur(Node node, char path[], int pathLen) {
        if (node == null)
            return;
        path[pathLen] = node.name;
        pathLen++;

        if (node.leftChild == null && node.rightChild == null)
            printArray(path, pathLen);
        else {
            printPathsRecur(node.leftChild, path, pathLen);
            printPathsRecur(node.rightChild, path, pathLen);
        }
    }

    static void printArray(char chars[], int len) {
        String wynik = new String();
        for (int i = 0; i < len; i++) {
            wynik+=chars[len-1-i];
        }
        if(wynik.compareTo(Result)>0){
            Result=wynik;
        }
    }
}
class Node {

    char name;
    Node leftChild;
    Node rightChild;

    Node(char name) {
        this.name = name;
    }
    Node() {
    }

    public String toString() {
        return String.valueOf(name);
    }
}

