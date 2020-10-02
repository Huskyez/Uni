package sorter;

import java.util.ArrayList;

public class QuickSort implements AbstractSorter {

    private ArrayList<Integer> intArray;

    public QuickSort(ArrayList<Integer> intArray) {
        this.intArray = intArray;
    }

    static int partition(ArrayList<Integer> arr, int low, int high)
    {
        int pivot = arr.get(high);
        int i = (low-1);
        for (int j = low; j < high; j++)
        {
            if (arr.get(j) <= pivot)
            {
                i++;
                int temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);
            }
        }
        int temp = arr.get(i+1);
        arr.set(i+1, arr.get(high));
        arr.set(high, temp);
        return i+1;
    }


    static void sorting(ArrayList<Integer> arr, int low, int high)
    {
        if (low < high)
        {
            int pi = partition(arr, low, high);

            sorting(arr, low, pi-1);
            sorting(arr, pi+1, high);
        }
    }

    @Override
    public ArrayList<Integer> sort() {
        sorting(intArray, 0, intArray.size()-1);
        return intArray;
    }
}
