package org.example.SOLID;

public class OCPBeforeDescending {
    public static void main(String[] args) {
        int arr[] = {5, 1, 4, 2, 3};

        // Hardcoded descending sort
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] < arr[j]) { // descending
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        System.out.println("Descending order:");
        for (int element : arr) {
            System.out.print(element + " ");
        }
    }
}
