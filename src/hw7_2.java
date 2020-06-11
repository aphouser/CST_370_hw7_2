/*
 * HackerRank link:https://www.hackerrank.com/contests/cst370-su20-hw7/challenges/coin-row/submissions/code/1324207180
 * Title: hw7_2.java
 * Abstract: Reads in numbers by user and solves coin sort problem
 * Author: Adam Houser
 * ID: 1144
 * Date: 6/10/2020
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class hw7_2 {

    public static void main(String[] args) {

        ArrayList<Integer> coins = new ArrayList();
        ArrayList<Integer> values = new ArrayList();
        ArrayList<Integer> maxesOutput = new ArrayList();

        Scanner in = new Scanner(System.in);

        // read in the number of coins for coin problem
        int num = in.nextInt();

        // seed 0 for coins list
        coins.add(0);

        // add rest of coins
        for (int i = 0; i < num; i++) {
            coins.add(in.nextInt());
        }

        in.close();

        // create array for max values
        int maxes[] = new int[num+1];
        Arrays.fill(maxes, 0);

        // calculate cost using recursive dynamic program
        for (int i = 0; i <= num; i++) {
            values.add(coinCost(coins, maxes, i));
        }

        // use our maxes[] to backtrack and find the coins picked up
        for (int i = num; i > 0; i--) {
            if(maxes[i] == i) {
                maxesOutput.add(i);
                i--;
            }
        }

        // reverse maxesOutput since we built it backwards
        Collections.reverse(maxesOutput);

        System.out.print("Best set:");
        for (int i = 0; i < maxesOutput.size(); i++) {
            System.out.print(maxesOutput.get(i) + " ");
        }
        System.out.println();
        System.out.println("Max value:" + values.get(num));
    }

    static int coinCost(ArrayList<Integer> coins, int[] maxes, int index)
    {
        // initial conditions
        if (index == 0) {
            return 0;
        }

        if (index == 1) {
            maxes[1] = 1;
            return coins.get(1);
        }

        // otherwise, use recursion
        //return Math.max(coinCost(coins, index-2) + coins.get(index), coinCost(coins, index-1));
        int current = coinCost(coins, maxes,index-2) + coins.get(index);
        int prev = coinCost(coins, maxes, index-1);

        if (current > prev) {
            maxes[index] = index;
            return current;
        }
        else {
            maxes[index] = index-1;
            return prev;
        }
    }
}
