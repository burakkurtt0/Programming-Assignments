import java.lang.Math;
import java.util.*;

public class Algos {
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static int[] SelectionSort(int[] arr) {
        int[] sorted_arr = arr;
        int Size = sorted_arr.length;
        for (int i = 0; i < Size - 1; i++) {
            int min = i;
            for (int j = i + 1; j < Size; j++) {
                if (sorted_arr[j] < sorted_arr[min]) {
                    min = j;
                }
            }
            if (min != i) {
                swap(sorted_arr, min, i);
            }
        }
        return sorted_arr;
    }

    public static int[] QuickSort(int[] arr) {
        int[] sorted_arr = arr;

        QuickSort(sorted_arr, 0, arr.length - 1);

        return sorted_arr;
    }

    private static void QuickSort(int[] arr, int low, int high) {
        int Size = high - low + 1;
        int[] stack = new int[Size];
        int top = -1;
        stack[++top] = low;
        stack[++top] = high;
        while (top >= 0) {
            high = stack[top--];
            low = stack[top--];
            int pivot = Partition(arr, low, high);
            if (pivot - 1 > low) {
                stack[++top] = low;
                stack[++top] = pivot - 1;

            }

            if (pivot + 1 < high) {
                stack[++top] = pivot + 1;
                stack[++top] = high;
            }
        }
    }

    private static int Partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;

    }

    // BUCKETSORT

    public static int[] BucketSort(int[] array) {
        int[] sorted_arr = BucketSort(array, 0);

        return sorted_arr;
    }

    private static int[] BucketSort(int[] array, int n) {
        int Bucket_Num = (int) Math.sqrt((double) array.length);
        ArrayList<Integer>[] buckets = new ArrayList[Bucket_Num];

        for (int i = 0; i < Bucket_Num; i++) {
            buckets[i] = new ArrayList<Integer>();
        }

        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        for (int i = 0; i < array.length; i++) {
            int hash_value = Hash(i, max, Bucket_Num);
            buckets[hash_value].add(array[i]);
        }

        for (int i = 0; i < Bucket_Num; i++) {
            Collections.sort(buckets[i]);
        }

        int[] sorted_arr = new int[array.length];
        int index = 0;
        for (int i = 0; i < Bucket_Num; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                sorted_arr[index++] = buckets[i].get(j);
            }
        }

        return sorted_arr;

    }

    private static int Hash(int i, int max, int Bucket_Num) {
        return (int) Math.floor(i / max * (Bucket_Num - 1));
    }

    public static int LinearSearch(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public static int BinarySearch(int[] array, int value) {
        int low = 0;
        int high = array.length - 1;

        while (high - low > 1) {
            int mid = (high + low) / 2;
            if (value > array[mid]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        if (array[low] == value) {
            return low;
        }
        if (array[high] == value) {
            return high;
        }
        return -1;
    }

}
