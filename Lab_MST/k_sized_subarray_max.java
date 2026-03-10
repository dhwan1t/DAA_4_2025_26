package Uni.DAA.Lab_MST;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class k_sized_subarray_max {
    public static ArrayList<Integer> maxOfSubarrays(int[] arr, int k) {
        // code here
        ArrayList<Integer> res = new ArrayList<>();
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        for (int i = 0; i < arr.length; i++) {
            q.add(new int[]{arr[i], i});

            while (!q.isEmpty() && q.peek()[1] <= i - k) q.poll();
            if (i >= k - 1) {
                res.add(q.peek()[0]);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 1, 4, 5, 2, 3, 6};
        int k = 3;
        System.out.println(maxOfSubarrays(arr, k));

    }
}
