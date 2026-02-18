package Uni.DAA.Session18;

public class UpperLowerBound {
    public static int lowerBound(int[] arr, int target){
        int lb = arr.length-1;
        int l = 0;
        int h = arr.length-1;

        while (l<=h) {
            int mid = l+(h-l)/2;
            if(arr[mid] >= target){
                lb = mid;
                h = mid-1;
            }
            else{
                l = mid+1;
            }
        }
        return lb;
    }

    public static int upperBound(int[] arr, int target) {
        int ub = arr.length-1;
        int l = 0;
        int h = arr.length-1;

        while (l<=h) {
            int mid = l+(h-l)/2;
            if(arr[mid] > target){
                ub = mid;
                h = mid-1;
            }
            else{
                l = mid+1;
            }
        }
        return ub;
    }

    public static int Ques1(int[] arr, int[] arr2, int sumTarget){
        int count = 0;
        for(int i=0; i<arr.length; i++){
            int get = sumTarget-arr[i];
            int up = upperBound(arr2, get);
            int lb = lowerBound(arr2, get);
            count += up-lb;
        }
        return count;
    }
    public static void main(String[] args) {
//        int[] arr = {0, 0, 1, 1, 2, 3, 4};
//        int target= 1;
//        int lb = lowerBound(arr, target);
//        int ub = upperBound(arr, target);
//        System.out.println("Lower bound: "+lb);
//        System.out.println("Upper bound: "+ub);
//        int ans =  ub-lb;
//        System.out.println("count of target: " + ans);

        int[] qarr1 = {5, 1, 1, 2, 3};
        int[] qarr2 = {1, 2, 4, 4, 6};
        int ansQues1 = Ques1(qarr1, qarr2, 5);
        System.out.println("Total pairs in both pairs that give 6: "+ ansQues1);

    }
}
