class Solution {
    public int findPeakElement(int[] arr) {
int start = 0;
        int end = arr.length-1;

        while(start < end){
            int mid = start+(end-start)/2;
            if(arr[mid] > arr[mid+1]){
                //you are in dec part of array
                //this maybe ans but look at LHS
                //this is why end != mid-1
                end = mid;
            }
            else{
                //you are in asc part of array
                start = mid+1;
                //bcz we know that mid+1 element > mid element

            }

        }
        //in the end . start = end and pointing to the largest number bcz of 2 checks above
        return start;
    }
}