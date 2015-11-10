package com.joke.algospot.lis;

import java.util.Scanner;

/**
 * https://algospot.com/judge/problem/read/LIS
 */
public class LongestIncreasingSequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int caseCount = scanner.nextInt();
        int[] result = new int[caseCount];

        LongestIncreasingSequence lis = new LongestIncreasingSequence();
        for(int i = 0; i < caseCount; i++) {
            int sequenceLength = scanner.nextInt();

            int[] sequence = new int[sequenceLength];
            for(int j = 0; j < sequenceLength; j++) {
                sequence[j] = scanner.nextInt();
            }

            result[i] = lis.solve(sequence);
        }

        for(int r : result) {
            System.out.println(r);
        }
    }

    private int solve(int[] sequence) {
        int[] maxLengths = new int[sequence.length];
        int maxLength = 1;

        for(int i = 0; i < sequence.length; i++) {
            maxLengths[i] = 1;
            for(int j = 0; j < i; j++) {
                if (sequence[j] < sequence[i] && maxLengths[j] + 1 > maxLengths[i]) {
                    maxLengths[i] = maxLengths[j] + 1;
                }
            }

            if (maxLength < maxLengths[i]) {
                maxLength = maxLengths[i];
            }
        }

        return maxLength;
    }
}
