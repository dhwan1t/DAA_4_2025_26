package Uni.DAA.Session18.AggresiveCows;

import java.util.Arrays;

public class AggresiveCows {
    public static boolean helper(int[] stalls, int k, int dist) {
        int count = 1;
        int last = stalls[0];

        for(int i = 1; i < stalls.length; i++){
            if (stalls[i] - last>=dist) {
                count++;
                last = stalls[i];
                if(count == k)return true;
            }
        }
        return false;
    }

    public static int aggressiveCows(int[] stalls, int k) {
        Arrays.sort(stalls);

        int l = 1;
        int h = stalls[stalls.length-1] - stalls[0];
        int ans = 0;
        while (l<=h) {
            int mid = l+(h-l)/2;
            if(helper(stalls, k, mid)){
                ans = mid;
                l = mid+1;
            }
            else{
                h = mid-1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 8, 9};
        int k = 3;
        int ans = aggressiveCows(arr, k);
        System.out.println(ans);
    }
}
