package Uni.DAA.Session18.KokoEatingBananas;

class KokoEatingBananas{
    public static int helper(int[] arr, int mid){
        int sum = 0;
        for(int i=0; i<arr.length; i++){
            sum += Math.ceil((double)arr[i]/mid );
        }
        return sum;
    }
    public static int eating(int[] piles, int h) {
        int l = 1;
        int hi = 0;

        for(int i=0; i<piles.length; i++){
            hi = Math.max(hi, piles[i]);
        }
        while(l<hi){
            int mid = l+(hi-l)/2;
            int sum = helper(piles, mid);

            if(h >= sum){
                hi = mid;
            }
            else{
                l = mid+1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        int[] piles = {3, 6, 7, 11};
        int h = 8;
        int ans = eating(piles, h);
        System.out.println(ans);
    }
}