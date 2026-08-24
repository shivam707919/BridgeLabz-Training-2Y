import java.util.*;
public class MergeSort{
    static void merge(int arr[],int left[],int right[]){
        int i=0;
        int j=0;
        int k=0;
        while(i<left.length&&j<right.length){
            if(left[i]<right[j]){
                arr[k]=left[i];
                i++;
            }else{
                arr[k]=right[j];
                j++;
            }
            k++;
        }
        while(i<left.length){
            arr[k]=left[i];
            i++;
            k++;
        }
        while(j<right.length){
            arr[k]=right[j];
            j++;
            k++;
        }
    }
    static void mergeSort(int arr[]){
        if(arr.length<=1){
            return;
        }
        int mid=arr.length/2;
        int left[]=new int[mid];
        int right[]=new int[arr.length-mid];
        for(int i=0;i<mid;i++){
            left[i]=arr[i];
        }
        for(int i=mid;i<arr.length;i++){
            right[i-mid]=arr[i];
        }
        mergeSort(left);
        mergeSort(right);
        merge(arr,left,right);
    }
    public static void main(String[]args){
        int arr[]={8,61,2,3,4,0};
        mergeSort(arr);
        System.out.println("Sorted Array:");
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }
}
/*
Division:
{8,61,2,3,4,0}
Split into
{8,61,2} and {3,4,0}
Then
{8,61} {2} and {3,4} {0}
Finally
{8} {61} {2} {3} {4} {0}
T(N)=2T(N/2)+N
Time Complexity: O(NlogN)
*/