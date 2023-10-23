package sort_N2;

import java.util.Arrays;
import java.util.Comparator;

public class SystemSortTest {


    public static void main(String[] args) {
        Integer[] array = {3, 24, 10, 5, 87, 1243, 13, 204, 4};

        // Arrays.sort(array, (a, b) -> b - a); or
        Arrays.sort(array, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });

        System.out.println(Arrays.toString(array));

    }
}
