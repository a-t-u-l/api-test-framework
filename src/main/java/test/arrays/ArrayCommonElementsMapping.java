package test.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/***********************************************************************************************************************
 * #1)
 * Write a program to find common elements from two ArrayList of Integers, without using a Set or Map.
 * public ArrayList<Integer>  getCommonItems(ArrayList<Integer> originalList1, ArrayList<Integer> originalList2)
 * {
 * }
 * 
 * Ans: I have defined two approaches without using a Set or Map. 

 * How does using a Set data structure make a difference.
 * 
 * Ans: As Set does not all duplicate values it does not require additional check for duplicates.

 * Can you quantify the time difference in execution as the dataset size grows, between the method that uses HashSet vs 
 * the method that does not use any Hash based data structures for de-duping the original List?
 * 
 *  * Ans: I am assuming this question is regarding time difference between two approaches, please find my observations below:
 *  *
 *  * For randomized data between range :(10,1000000) And ArrayList Size : 1000000
 *  * Method: getCommonItems : time taken : 829 ms
 *  * getCommonItemsUsingSet : time taken : 873 ms
 *  * getCommonItemsUsingMap : time taken : 186 ms
 *  * getCommonItemsWithReducedTime : time taken : 221 ms
 *
 *  * For randomized data between range :(10,10000000) And ArrayList Size : 10000000
 *  * Method: getCommonItems : time taken : 9902 ms
 *  * getCommonItemsUsingSet : time taken : 11260 ms
 *  * getCommonItemsUsingMap : time taken : 2419 ms
 *  * getCommonItemsWithReducedTime : time taken : 2366 ms
 *  
 *  As from various samples taken (including above two) the two methods : *getCommonItemsUsingMap()* 
 *  and *getCommonItemsWithReducedTime()* give equivalent performance.


 * Write unit tests to  ensure your algorithm is correct. For many input lists.
 * ********************************************************************************************************************/

public class ArrayCommonElementsMapping {

	/***********************************************************************************************************************
	 * Sorts the given lists in the argument and searches for the common key using binary search.
	 *  
	 * @param First list of Integer elements
	 * @param Second list of Integer elements
     * 
     * @return List of Integers containing common elements for the given list
	 * ********************************************************************************************************************/
	public List<Integer>  getCommonItems(List<Integer> originalList1, List<Integer> originalList2)
	{
		Collections.sort(originalList1); //O(n * log n)
		Collections.sort(originalList2); //O(n * log n)
		ArrayList<Integer> commonElements = new ArrayList<Integer>();
		if(originalList1.isEmpty() || originalList2.isEmpty()){
			return commonElements;
		}
		int previous=0;
		for(int number : originalList1){
			int position = Collections.binarySearch(originalList2, number);
			if(position > -1){
				if(previous!=number)
					commonElements.add(number);
			}
			previous=number;
		}//O(n) * //O(log n) = //O(n * log n)
		return commonElements;
	} // Worst case ~ O(n * log n)

	/***********************************************************************************************************************
	 * Sorts the given lists in the argument and searches for the common key using modified binary search.
	 *  It keeps on utilizing the found key position to reduce the search range thus increasing the performance.
	 *  
	 * @param First list of Integer elements
	 * @param Second list of Integer elements
     * 
     * @return List of Integers containing common elements for the given list
	 * ********************************************************************************************************************/
	public List<Integer> getCommonItemsWithReducedTime(List<Integer> originalList1, List<Integer> originalList2)
	{
		Collections.sort(originalList1); //O(n * log n)
		Collections.sort(originalList2); //O(n * log n)
		List<Integer> commonElements = new ArrayList<Integer>();
		if(originalList1.isEmpty() || originalList2.isEmpty()){
			return commonElements;
		}
		int previous = 0; int previousNum=0;
		int lastIndex = originalList2.size()-1;
		for(int number : originalList1){
			int result = binarySearch(originalList2, number, previous, lastIndex);
			if(result!=-1){
				if(previousNum!=number)
					commonElements.add(number);
				previous = result;
			}
			previousNum=number;
		}//O(n) * //O(log n) = //O(n * log n)
		return commonElements;
	}// Worst case ~ O(n * log n) - but in average cases it shows much improved performance as in binary search we keep decreasing
	//the list size if any of the common element is found.

	/***********************************************************************************************************************
	 * * Searches the specified list for the specified object using the modified binary
     * search algorithm. The list must be sorted into ascending order prior to making this
     * call.  If it is not sorted, the results are undefined.  If the list
     * contains multiple elements equal to the specified object, there is no
     * guarantee which one will be found.
     * 
     * @param list of Integer elements
     * @param search key
     * @param starting point for the search range
     * @param end point for the search range, where low <= high
     * 
     * @return int value= -1 if key is not found else the position of the key in the list
	 * ********************************************************************************************************************/
	private int binarySearch(List<Integer> list, int key, int low, int high){
		int mid = low + (high - low)/2;
		if(low<=high){
			if( list.get(mid) == key)
				return mid;
			else if(list.get(mid) < key)
				return binarySearch( list, key, mid+1, high);
			else
				return binarySearch( list, key, low, mid-1);
		}
		return -1;
	}

	/***********************************************************************************************************************
	 * Stores the common elements using properties of HashMap which allows only unique key as key-set.
	 *  It uses Set to allow addition of only unique keys to commonElements.
	 *  
	 * @param First list of Integer elements
	 * @param Second list of Integer elements
     * 
     * @return List of Integers containing common elements for the given list
	 * ********************************************************************************************************************/
	public List<Integer>  getCommonItemsUsingMap(List<Integer> originalList1, List<Integer> originalList2)
	{
		Map<Integer, Integer> items1 = new HashMap<Integer, Integer>();
		Set<Integer> commonElements = new HashSet<Integer>();

		for (int index = 0; index < originalList1.size(); index++){
			items1.put(originalList1.get(index), 1);
		}
		for (int index = 0; index < originalList2.size(); index++){
			int number = originalList2.get(index);
			if(items1.containsKey(number)){
				commonElements.add(number);
			};
		}
		List<Integer> list = new ArrayList<>(commonElements);
		return list;
	}
	
	/***********************************************************************************************************************
	 * Converts the List to Set to remove all the duplicates Then stores the common elements using 
	 * Set method retainAll to allow addition of only unique keys to commonElements.
	 *  
	 * @param First list of Integer elements
	 * @param Second list of Integer elements
     * 
     * @return List of Integers containing common elements for the given list
	 * ********************************************************************************************************************/
	public List<Integer>  getCommonItemsUsingSet(List<Integer> originalList1, List<Integer> originalList2)
	{
		Set<Integer> items1 = new HashSet<Integer>(originalList1);
		Set<Integer> items2 = new HashSet<Integer>(originalList2);
		items2.retainAll(items1);
		return new ArrayList<>(items2);
	}

	/***********************************************************************************************************************
	 * Main method to validate the functionality and performance. 
	 * TODO: Break this to unit tests.
	 * ********************************************************************************************************************/
	public static void main(String [] args){
		ArrayCommonElementsMapping mapping = new ArrayCommonElementsMapping();
		List<Integer> originalList1 = new ArrayList<Integer>();
		List<Integer> originalList2 = new ArrayList<Integer>();

		for (int i=0; i<10000000; i++){
			Random r = new Random();
			int Low = 10;
			int High = 10000000;
			int num = r.nextInt(High-Low) + Low;
			originalList1.add(num);
		}

		for (int i=0; i<10000000; i++){
			Random r = new Random();
			int Low = 10;
			int High = 100000000;
			int num = r.nextInt(High-Low) + Low;
			originalList2.add(num);
		}

//		for(int i : originalList1)
//			System.out.print(" "+i+" ");
//		System.out.println("\n");
//		for(int i : originalList2)
//			System.out.print(" "+i+" ");
//		System.out.println("\n");

		System.out.println("getCommonItems : ");
		long start = System.currentTimeMillis();
		List<Integer> list1 = mapping.getCommonItems(originalList1, originalList2);
//		for(int i : list1)
//			System.out.print(" "+i+" ");
		long stop = System.currentTimeMillis();
		long time = ((stop - start));
		System.out.println("\ntime taken : "+ time +" ms");

		System.out.println("\ngetCommonItemsUsingSet : ");
		long start1 = System.currentTimeMillis();
		List<Integer> list2 = mapping.getCommonItemsUsingSet(originalList1, originalList2);
//		for(int i : list2)
//			System.out.print(" "+i+" ");
		long stop1 = System.currentTimeMillis();
		long time1 = ((stop1 - start1));
		System.out.println("\ntime taken : "+ time1 +" ms");

		System.out.println("\ngetCommonItemsUsingMap : ");
		long startM = System.currentTimeMillis();
		List<Integer> listM = mapping.getCommonItemsUsingMap(originalList1, originalList2);
//		for(int i : listM)
//			System.out.print(" "+i+" ");
		long stopM = System.currentTimeMillis();
		long timeM = ((stopM - startM));
		System.out.println("\ntime taken : "+ timeM +" ms");
		
		System.out.println("\ngetCommonItemsWithReducedTime : ");
		long start2 = System.currentTimeMillis();
		List<Integer> list3 = mapping.getCommonItemsWithReducedTime(originalList1, originalList2);
//		for(int i : list3)
//			System.out.print(" "+i+" ");
		long stop2 = System.currentTimeMillis();
		long time2 = ((stop2 - start2));
		System.out.println("\ntime taken : "+ time2 +" ms");
	}

}
