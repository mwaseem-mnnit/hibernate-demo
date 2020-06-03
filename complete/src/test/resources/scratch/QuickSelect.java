// Java program of Quick Select

import java.util.Arrays;

class Solution {
    public static int partition(int[] arr,
                                int low, int high) {
        int pivot = arr[high], pivotloc = low;
        for (int i = low; i <= high; i++) {
            if (arr[i] < pivot) {
                int temp = arr[i];
                arr[i] = arr[pivotloc];
                arr[pivotloc] = temp;
                pivotloc++;
            }
        }
        int temp = arr[high];
        arr[high] = arr[pivotloc];
        arr[pivotloc] = temp;
        return pivotloc;
    }

    public static int kthSmallest(int[] arr, int low,
                                  int high, int k) {
        int partition = partition(arr, low, high);
        if (partition == k)
            return arr[partition];
        else if (partition < k)
            return kthSmallest(arr, partition + 1, high, k);
        else
            return kthSmallest(arr, low, partition - 1, k);
    }

    public static void main(String[] args) {
        System.out.println(kthSmallest(new int[]{10, 4, 5, 8, 6, 11, 26}, 0, 6, 6));
        System.out.println(kthSmallest(new int[]{10, 4, 4, 8, 6, 11, 26}, 0, 6, 0));
        System.out.println(kthSmallest(new int[]{10, 4, 4, 6, 6, 6, 26}, 0, 6, 2));
        System.out.println(kthSmallest(new int[]{10, 4, 4, 6, 6, 6, 26}, 0, 6, 4));
    }
}
