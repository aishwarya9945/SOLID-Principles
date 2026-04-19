package org.example.SOLID;

interface ValueComparator {
    int compare(int value1, int value2);
}

class ArrayUtil {
    public static void sort(int[] a, ValueComparator comparator) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (comparator.compare(a[i], a[j]) > 0) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }
}

class AscComparator implements ValueComparator {
    @Override
    public int compare(int value1, int value2) {
        return value1 - value2;
    }
}

class DescComparator implements ValueComparator {
    @Override
    public int compare(int value1, int value2) {
        return value2 - value1;
    }
}

public class OCPAfterWithComparatorInterface {
    public static void main(String[] args) {
        int arr[] = {5, 1, 4, 2, 3};

        System.out.println("Ascending order:");
        ArrayUtil.sort(arr, new AscComparator());
        for (int element : arr) {
            System.out.print(element + " ");
        }

        System.out.println("\nDescending order:");
        ArrayUtil.sort(arr, new DescComparator());
        for (int element : arr) {
            System.out.print(element + " ");
        }
    }
}
