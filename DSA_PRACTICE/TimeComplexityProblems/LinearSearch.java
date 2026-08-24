import java.util.*;
public class LinearSearch{
    public static void main(String[]args){
        int arr[]={8,61,2,3,4,0};
        int target=4;
        int index=-1;
        for(int i=0;i<arr.length;i++){
            if(arr[i]==target){
                index=i;
                break;
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
Problem TC-1: Linear Search Time Complexity
Sample Array:
{8,61,2,3,4,0}
1. Best Case:
If the target is 8, it is found at the first position.
Only one comparison is needed.
Time Complexity: O(1)
2. Worst Case:
If the target is 0 or a number like 5 which is not in the array,
the whole array is checked.
Time Complexity: O(N)
3. Average Case:
The target can be at any position in the array.
On average, about half of the elements are checked.
Time Complexity: O(N)
*/