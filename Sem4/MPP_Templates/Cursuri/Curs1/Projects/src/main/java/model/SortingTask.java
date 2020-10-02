package model;

import sorter.AbstractSorter;
import sorter.BubbleSort;
import sorter.QuickSort;
import sorter.SortingMethods;

import java.util.ArrayList;

public class SortingTask extends Task{

    private SortingMethods strategy;
    private ArrayList<Integer> intArray;

    public SortingTask(String taskId, String descriere, SortingMethods strategy, ArrayList<Integer> intArray) {
        super(taskId, descriere);
        this.strategy = strategy;
        this.intArray = intArray;
    }


    @Override
    public void execute() {
        AbstractSorter sort;
        if(strategy == SortingMethods.BubbleSort){
            sort = new BubbleSort(intArray);
        }
        else{
            sort = new QuickSort(intArray);
        }
        ArrayList<Integer> arr = sort.sort();
        for(Integer i : arr){
            System.out.print(i +" ");
        }
        System.out.println();
    }
}


