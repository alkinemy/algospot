package com.joke.algospot.festival;

import java.util.Scanner;


/**
 * https://algospot.com/judge/problem/read/FESTIVAL
 */
public class Festival {
    public static void main(String args[]) {
        new Festival().solve();
    }

    public void solve() {
        Scanner scanner = new Scanner(System.in);
        String caseCountInput = scanner.nextLine();
        int caseCount = Integer.parseInt(caseCountInput);
        double[] result = new double[caseCount];

        for(int i = 0; i < caseCount; i++) {
            String dayInput = scanner.nextLine();
            String[] daySplit = dayInput.split(" ");
            int rentMaxDay = Integer.parseInt(daySplit[0]);
            int rentMinDay = Integer.parseInt(daySplit[1]);

            String costInput = scanner.nextLine();
            String[] costSplit = costInput.split(" ");
            Integer[] cost = new Integer[rentMaxDay];
            for(int j = 0; j < rentMaxDay; j++) {
                cost[j] = Integer.parseInt(costSplit[j]);
            }

            result[i] = solve(rentMinDay, rentMaxDay, cost);
        }

        for(int i = 0; i < caseCount; i++) {
            System.out.format("%.10f\n", result[i]);
        }
    }

    private double solve(int rentMinDay, int rentMaxDay, Integer[] cost) {
        double min = 100d;
        //x: rentDay, y: startDay
        double[][] average = new double[rentMaxDay][rentMaxDay];

        for(int startDay = 0; startDay < rentMaxDay; startDay++) {
            if (rentMinDay == 1 && cost[startDay] < min) {
                min = cost[startDay];
            }
            average[0][startDay] = cost[startDay];
        }

        for(int rentDay = 1; rentDay < rentMaxDay; rentDay++) {
            for(int startDay = 0; startDay < rentMaxDay - rentDay; startDay++) {
                double currentAverage = (rentDay * average[rentDay - 1][startDay] + average[0][startDay + rentDay]) / (double) (rentDay + 1);
                if (rentDay + 1 >= rentMinDay && currentAverage < min) {
                    min = currentAverage;
                }
                average[rentDay][startDay] = currentAverage;
            }
        }

        return min;
    }
}
