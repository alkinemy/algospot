package com.joke.algospot.quadtree;

import java.util.Scanner;

/**
 * https://algospot.com/judge/problem/read/QUADTREE
 */
public class QuadTree {

    private String tree;

    public QuadTree(String tree) {
        this.tree = tree;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int caseCount = scanner.nextInt();
        String[] result = new String[caseCount];

        for(int i = 0; i < caseCount; i++) {
            String tree = scanner.next();
            result[i] = new QuadTree(tree).solve();
        }

        for(int i = 0; i < caseCount; i++) {
            System.out.println(result[i]);
        }
    }

    private String solve() {
        return assemble(tree);
    }

    private String assemble(String tree) {
        if ("w".equals(tree) || "b".equals(tree)) {
            return tree;
        }

        String[] parts = getParts(tree);
        return new StringBuilder().append("x").append(assemble(parts[2])).append(assemble(parts[3])).append(assemble(parts[0])).append(assemble(parts[1])).toString();
    }

    private String[] getParts(String tree) {
        String[] parts = new String[4];
        int startPoint = 1;
        for(int part = 0; part < 4; part++) {
            int current = startPoint;
            int readCount = 1;
            StringBuilder builder = new StringBuilder();
            while(readCount != 0 && startPoint < tree.length()) {
                char color = tree.charAt(current);
                if (color == 'x') {
                    readCount += 4;
                }
                builder.append(color);
                readCount--;
                current++;
            }
            parts[part] = builder.toString();
            startPoint = current;
        }
        return parts;
    }
}
