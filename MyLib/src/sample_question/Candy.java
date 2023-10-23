package sample_question;

/*
 * There are n children standing in a line.
 * Each child is assigned a rating value given in the integer array ratings.
 *
 * following requirements:
 * 1. Each child must have at least one candy.
 * 2. Children with a higher rating get more candies than their neighbors.
 *
 * Return the minimum number of candies you need to have to distribute
 * the candies to the children.
 */
public class Candy {
    public static int candy(int[] ratings) {
        int length = ratings.length;
        int[] left = new int[length];
        int[] right = new int[length];
        int leftSlope = 1, rightSlope = 1;

        left[0] = leftSlope;
        right[length - 1] = rightSlope;

        for (int i = 1; i < length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = ++leftSlope;
            } else {
                leftSlope = 1;
                left[i] = 1;
            }
        }

        for (int i = length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = ++rightSlope;
            } else {
                rightSlope = 1;
                right[i] = 1;
            }
        }

        int candy = 0;
        for (int i = 0; i < length; i++) {
            candy += Math.max(left[i], right[i]);
        }
        return candy;
    }

    public static void main(String[] args) {
        int[] children = {1, 2, 3, 4, 5, 3, 2, 2, 4, 1, 2};
        System.out.println(candy(children));
    }
}
