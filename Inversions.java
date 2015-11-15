import java.io.*;
import java.util.Scanner; 

public class Inversions {
	
	private static long sortAndCount(int[] inarr, int lo, int hi){
		if (lo == hi - 1) {
			return 0;
		}
		int mid = (lo + hi)/2;
		//recursive calls
		long x = sortAndCount(inarr, lo, mid);
		long y = sortAndCount(inarr, mid, hi);
		//merge call
		long z = sortAndMerge(inarr, lo, mid, hi);
		//return inversion count
		return x+y+z;
	}
	
	private static long sortAndMerge(int[] inarr, int lo, int mid, int hi){
		long count = 0;
		int[] outarr = new int[inarr.length];
		
		//keeping track of positions in array
		int lo2 = lo;
		int mid2 = mid;
		
		for(int i = lo; i < hi; i++) {
			//copy to output array
			if (mid2 >= hi || lo2 < mid && inarr[lo2] <= inarr[mid2]) {
				outarr[i] = inarr[lo2++];
			}
			else {
				//increment count and copy to output array
				count = count + (mid - lo2);
				outarr[i] = inarr[mid2++];
			}
		}
		
		System.arraycopy(outarr, lo, inarr, lo, hi-lo);
		return count;
	}
	
	
	public static long countInversions(int[] inarr) {
		return sortAndCount(inarr, 0, inarr.length);
	}
	
	public static void main(String[] args){
		try
		{
			Scanner scanner = new Scanner(new File("IntegerArray.txt"));
			int [] tstin = new int [100000];
			int i = 0;
			while(scanner.hasNextInt())
			{
			     tstin[i++] = scanner.nextInt();
			}
			long totcnt = countInversions(tstin);
			System.out.println(totcnt);
		}
		catch (FileNotFoundException ex)
		{
			System.out.println("Nope lol");
		}
		//int [] tstin = {1, 3, 5, 2, 4, 6};
	}
	
}