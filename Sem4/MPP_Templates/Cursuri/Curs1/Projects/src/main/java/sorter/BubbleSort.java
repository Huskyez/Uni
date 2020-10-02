package sorter;

import java.util.ArrayList;

public class BubbleSort implements AbstractSorter {

    private ArrayList<Integer> intArray;

    public BubbleSort(ArrayList<Integer> intArray) {
        this.intArray = intArray;
    }

    @Override
    public ArrayList<Integer> sort() {
        int n = intArray.size();
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (intArray.get(j) > intArray.get(j+1))
                {
                    // swap temp and arr[i]
                    int temp = intArray.get(j);
                    intArray.set(j,intArray.get(j+1));
                    intArray.set(j+1,temp);
                }
                return intArray;
    }

}
