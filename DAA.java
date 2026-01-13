package DAA_13_Jan;

public class DAA {
    private static void reverseArray(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
    public static int operations = 0;
    public static int maxDepth = 0;


    static void complexRec(int n) {
        if (n <= 2) {
            return;
        }
        int p = n;
        while (p > 0) {
            int[] temp = new int[n];
            for (int i = 0; i < n; i++) {
                temp[i] = i ^ p;
            }
            operations++;
            p >>= 1;
        }
//        work done in one call O(nlogn)
        int[] small = new int[n];
        for (int i = 0; i < n; i++) {
            small[i] = i * i;
            operations++;
        }

        reverseArray(small);
        complexRec(n / 2);
        complexRec(n / 2);
        complexRec(n / 2);
    }

    public static void main(String[] args) {
        complexRec(16);
        System.out.println(operations);
//     Recurrence relation- T(n) = 3T(n/2)+O(nlogn)+n/2+n
//     Master Theorem- O(n^log(base 2)3)
    }
}
