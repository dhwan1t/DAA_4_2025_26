package Uni.DAA.Practical_EST;

public class LongestIncreasingSubsequence {

    public static int increases(int[] arr){
        int min = Integer.MAX_VALUE;
        int idx = 0;
        int count = 0;

        for(int i=0; i<arr.length; i++){
            if(arr[i] < min){
                min = arr[i];
                idx = i;
            }
        }
        for(int i=idx; i<arr.length-1; i++){
            if(arr[i] < arr[i+1]){
                count++;
            }
        }
        return count+1;
    }
    public static void main(String[] args) {
        int[] arr = {10, 9, 2, 5, 3, 7, 101, 8};
        System.out.println(increases(arr));
    }
}
