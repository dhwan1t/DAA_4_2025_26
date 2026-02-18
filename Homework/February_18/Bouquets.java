package Uni.DAA.Homework.February_18;

public class Bouquets {
    public static int getBouquets(int[] arr, int mid, int k){
        int count=0;
        int b=0;
        for(int i=0; i<arr.length; i++){
            if(arr[i] <= mid) count++;
            else count=0;
            if(count == k) {
                b++;
                count=0;
            }
        }
        return b;
    }

    public static int minDays(int[] arr, int m, int k) {
        int min = 0;
        int max = arr[0];
        int days =-1;
        for(int i: arr){
            min = Math.min(min,i);
            max = Math.max(max, i);
        }
        while(min<=max){
            int mid = min+(max-min)/2;
            if(getBouquets(arr,mid,k)>=m){
                days = mid;
                max = mid-1;
            }
            else{
                min = mid+1;
            }
        }
        return days;
    }

    public static void main(String[] args) {
        int[] arr = {1,10,3,10,2};
        int m = 3, k = 1;
        int ans = minDays(arr, m, k);
        System.out.println(ans);
    }
}
