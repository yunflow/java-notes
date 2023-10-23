package sample_question;

import java.util.Arrays;

/*
 * input: an array whose elements are arrays of 3 ints
 * [color, cost to red, cost to blue]
 * color: 0-no color, 1-red, 2-blue
 * [0, 4, 7]: no color, if change to red cost 4, if change to blue cost 7
 * [1, x, x]: red color, cannot change color anymore
 *
 * output: Satisfy that there are as many as two colors with minimum cost
 * -1 if unable to meet conditions
 */
public class MagicStone {
    public static int getMinimumCost(int[][] stones) {
        // If the number of stones is not even, the condition cannot be satisfied
        int n = stones.length;
        if ((n & 1) != 0) { // eg. 11010 & 00001 = 0, 11011 & 00001 = 1
            return -1;
        }

        // Arrays.sort(stones, (a, b) -> a[0] == 0 && b[0] == 0 ? (b[1] - b[2] - a[1] + a[2]) : (a[0] - b[0]));
        Arrays.sort(stones, (a, b) -> {
            if (a[0] == 0 && b[0] == 0) {
                return b[1] - b[2] - a[1] + a[2];
            } else {
                return (a[0] - b[0]);
            }
        });

        int noColor = 0, red = 0, blue = 0, cost = 0;
        for (int[] stone : stones) {
            if (stone[0] == 0) {
                noColor++;
                cost += stone[1]; // Calculate the cost of all red
            } else if (stone[0] == 1) {
                red++;
            } else {
                blue++;
            }
        }

        // Check if red or blue is more than half
        if (red > (n >> 1) || blue > (n >> 1)) {
            return -1;
        }

        // How many blue still need
        blue = noColor - ((n >> 1) - red);

        // Subtract: blue-red consumption
        for (int i = 0; i < blue; i++) {
            cost += stones[i][2] - stones[i][1];
        }

        return cost;
    }

    public static void main(String[] args) {
        int[][] stones = {{1, 5, 3},
                {2, 7, 9},
                {0, 6, 3},
                {0, 7, 1},
                {0, 2, 1},
                {0, 5, 9},
                {1, 4, 10},
                {2, 5, 2},
                {2, 3, 5},
                {2, 9, 5}};
        System.out.println(getMinimumCost(stones));
    }
}
