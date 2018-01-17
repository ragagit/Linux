/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openclosed;

/**
 *
 * @author raga
 */
public class SortManager {
     
    public void sort( Sorter sorter){
        sorter.sort();
//        if( sorter.sortType == SortType.MERGESORT){
//            doInsertionSort(sorter);
//        }else{
//            doMergeSort(sorter);
//        }
    }
    
//    public void doInsertionSort(Sorter sorter){
//        sorter.sort();
//    }
//    
//    public void doMergeSort(Sorter sorter){
//        sorter.sort();
//    }
}
