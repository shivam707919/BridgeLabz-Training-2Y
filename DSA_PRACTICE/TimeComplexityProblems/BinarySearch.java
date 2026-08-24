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
Problem TC-2: Iterative Binary Search Time Complexity
Sample Sorted Array:
{0,2,3,4,8,61}
Searching for target 4
Step 1:
low=0
high=5
mid=2
arr[mid]=3
Since 4>3,
search in the right half.
Step 2:
low=3
high=5
mid=4
arr[mid]=8
Since 4<8,
search in the left half.
Step 3:
low=3
high=3
mid=3
arr[mid]=4
Target found.
Worst Case:
Every time the search space becomes half.
N
N/2
N/4
N/8
...
1
N/2^k=1
k=log₂N
Time Complexity: O(log N)
*/