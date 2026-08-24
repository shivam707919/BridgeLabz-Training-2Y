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
Problem SC-1: Linear Search Space Complexity
Sample Array:
{8,61,2,3,4,0}
Linear Search does not create any new array.
It only uses a few variables like:
target
index
i
So the extra memory used does not depend on the size of the array.
Auxiliary Space Complexity: O(1)
*/