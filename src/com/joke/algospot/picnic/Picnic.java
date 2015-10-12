package com.joke.algospot.picnic;

import java.util.Scanner;

public class Picnic {
    public static void main(String args[]) {
        new Picnic().solve();
    }

    private int sumOfPairs;
    private int[] cost;

    public void solve() {
        Scanner scanner = new Scanner(System.in);
        String caseCountInput = scanner.nextLine();
        int caseCount = Integer.parseInt(caseCountInput);
        int[] result = new int[caseCount];

        for(int i = 0; i < caseCount; i++) {
            String rawStudent = scanner.nextLine();
            String[] student = rawStudent.split(" ");
            int studentCount = Integer.parseInt(student[0]);
            int friendPairCount = Integer.parseInt(student[1]);

            String rawFriendPair = scanner.nextLine();
            String[] friendPairs = rawFriendPair.split(" ");
            cost = new int[friendPairCount];
            for(int j = 0; j < friendPairCount; j++) {
                cost[j] = (int) Math.pow(2, Integer.parseInt(friendPairs[2 * j])) + (int) Math.pow(2, Integer.parseInt(friendPairs[2 * j + 1]));
            }

            sumOfPairs = (int) Math.pow(2, studentCount) - 1;
            result[i] = solve(studentCount);
        }

        for(int i = 0; i < caseCount; i++) {
            System.out.println(result[i]);
        }
    }

    private int solve(int studentCount) {
        if (cost.length == 0) {
            return 0;
        }
        return solveRecursively(studentCount / 2, cost.length - 1, 0);
    }

    private int solveRecursively(int leftPairCount, int pivot, int sum) {
        if (leftPairCount == 0) {
            if (sum == sumOfPairs) {
                return 1;
            } else {
                return 0;
            }
        }

        if (pivot == 1) {
            if (leftPairCount == 3) {
                return 0;
            }

            if (leftPairCount == 2) {
                return solveRecursively(0, 0, sum + cost[0] + cost[1]);
            }
        }

        if (pivot == 0) {
            if (leftPairCount == 1) {
                return solveRecursively(0, 0, sum + cost[0]);
            }

            if (leftPairCount > 1) {
                return 0;
            }
        }

        int intermediate = sum + cost[pivot--];
        if (intermediate <= sumOfPairs) {
            //not selected + selected
            return solveRecursively(leftPairCount, pivot, sum) + solveRecursively(--leftPairCount, pivot, intermediate);
        } else {
            return solveRecursively(leftPairCount, pivot, sum);
        }
    }
}
