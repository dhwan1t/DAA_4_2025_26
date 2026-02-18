package Uni.DAA.Session18.PaintersPartition;

public class PaintersPartition {
    static boolean helper(int[] arr, int k, int maxTime) {
        int painters = 1;
        int currSum = 0;

        for (int x : arr) {
            if(x > maxTime) return false;
            if(currSum + x <= maxTime){
                currSum += x;
            }
            else{
                painters++;
                currSum = x;
                if (painters > k) return false;
            }
        }
        return true;
    }

    static int minTime(int[] arr, int k) {
        int l = 0, h = 0;
        for(int x : arr){
            l = Math.max(l, x);
            h += x;
        }
        int ans = h;

        while(l <= h){
            int mid = l+(h-l)/2;
            if (helper(arr, k, mid)) {
                ans = mid;
                h = mid-1;
            }
            else{
                l = mid+1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {5, 10, 30, 20, 15};
        int k = 3;
        System.out.println(minTime(arr, k));
    }
}
