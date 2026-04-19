package org.example.SOLID;

public class OCPBeforeAscending {
    public static void main(String[] args) {
        int arr[] = {5, 1, 4, 2, 3};

        // Hardcoded ascending sort
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) { // ascending
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        System.out.println("Ascending order:");
        for (int element : arr) {
            System.out.print(element + " ");
        }
    }
}
