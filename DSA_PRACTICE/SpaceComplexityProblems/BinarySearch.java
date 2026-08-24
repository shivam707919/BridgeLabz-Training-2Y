import java.util.*;
public class BinarySearch{
    public static void main(String[]args){
        int arr[]={0,2,3,4,8,61};
        int target=4;
        int low=0;
        int high=arr.length-1;
        int index=-1;
        while(low<=high){
            int mid=(low+high)/2;
            if(arr[mid]==target){
                index=mid;
                break;
            }else if(arr[mid]<target){
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        if(index!=-1){
            System.out.println("Element found at index "+index);
        }else{
            System.out.println("Element not found");
        }
    }
}
/*
Problem SC-2: Binary Search Space Complexity
Sample Sorted Array:
{0,2,3,4,8,61}
Iterative Binary Search only uses a few variables:
low
high
mid
target
index
It does not create any new array while searching.
So the extra memory used stays the same,
no matter how big the array is.
Auxiliary Space Complexity: O(1)
*/