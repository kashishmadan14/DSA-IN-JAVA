class Solution {
    public static void mergerSort(int arr[], int start, int end){
        if(end<=start){
            return;
        }
        int mid=start+(end-start)/2;
        mergerSort(arr, start, mid);
        mergerSort(arr, mid+1, end);

        int temp[]=new int[end-start+1];
        int i=start;
        int j=mid+1;
        int index=0;
        while(i<=mid && j<=end){
            if(arr[i]<arr[j]){
                temp[index]=arr[i];
                i++;
            }
            else if(arr[i]>=arr[j]){
                temp[index]=arr[j];
                j++;
            }
            index++;
        }
        while(i<=mid){
            temp[index++]=arr[i++];
        }
        while(j<=end){
            temp[index++]=arr[j++];
        }
        for(index=0,i=start; index<temp.length;){
            arr[i++]=temp[index++];
        }

    }
    public int[] sortArray(int[] nums) {    
    mergerSort(nums, 0, nums.length-1);
    return nums;
    }

}