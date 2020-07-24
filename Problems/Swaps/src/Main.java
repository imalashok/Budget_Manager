import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] line = scanner.nextLine().split(" ");
        int[] array = new int[line.length];

        for (int i = 0; i < line.length; i++) {
            array[i] = Integer.parseInt(line[i]);
        }

        System.out.println(bubbleSort(array));
    }


    public static int bubbleSort(int[] array) {
        int numberOfSwaps = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                /* if a pair of adjacent elements has the wrong order it swaps them */
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    numberOfSwaps += 1;
                }
            }
        }

        return numberOfSwaps;
    }
}