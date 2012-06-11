package sorting;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.number.OrderingComparison.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

public class QuickSort {
	private static Random rand = new Random(System.currentTimeMillis());
	
	private static  void checkArrayGetSorted(int[] toSort){
		System.out.println(Arrays.toString(toSort));
		QuickSort.sort(toSort,0,toSort.length);
		System.out.println(Arrays.toString(toSort));
		int last = Integer.MIN_VALUE;
		for(int i : toSort){
			assertThat(last, is(lessThanOrEqualTo(i)));
			last = i;
		}
	}
	
	@Test public void checkSimpleCase() {
		checkArrayGetSorted(new int[] { 5, 3, 2, 1});
	}

	@Test public void checkDuplicatesAreOk(){
		checkArrayGetSorted(new int[] { 5, 5, 3, 2, 1});
	}
	
	@Test public void checkAllSame(){
		checkArrayGetSorted(new int[] { 1,1,1,1,1});
	}

	@Test public void checkLongerArray(){
		checkArrayGetSorted(new int[] { 19, 5, 3, 4, -5, 6, 23, 23, 5, 3, 2, 1, 5, 6, 7 });
	}
	
	private static void sort(int[] toSort,int l, int r) {
		if(r-l < 2) return;
		int last = toSort[l];
		boolean allTheSame = true;
		for(int i =l; i < r && allTheSame; ++i){
			if(toSort[i] != last){
				allTheSame = false;
			}
		}
		if(allTheSame){
			return;
		}
		
		int mid = partition(toSort,l,r);
		sort(toSort,l,mid);
		sort(toSort,mid,r);
	}
	
	private static int partition(int[] toSort, int l, int r) {
		int sz = r-l;
		int pivotPos = rand.nextInt(sz) + l;
		int pivotVal = toSort[pivotPos];
		int nextSwap = l;
		for(int i = l; i < r; i++){
			if(toSort[i] < pivotVal){
				int tmp = toSort[nextSwap];
				toSort[nextSwap] = toSort[i];
				toSort[i] = tmp;
				nextSwap++;
			}
		}
		System.out.println(Arrays.toString(toSort));
		return nextSwap;
	}
}
