package DAA.Experiment_3;
import java.util.*;

    public class StableAttendanceWindow {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int n= sc.nextInt();
            HashMap<Integer, Integer> map = new HashMap<>();
            int prefixSum = 0;
            int maxLen = 0;

            map.put(0, -1);
            for(int i = 0; i < n; i++){
                char ch = sc.next().charAt(0);
                if (ch == 'P') {
                    prefixSum += 1;
                } else {
                    prefixSum -= 1;
                }
                if(map.containsKey(prefixSum)){
                    int prevIndex = map.get(prefixSum);
                    int length = i - prevIndex;
                    maxLen = Math.max(maxLen, length);
                }
                else{
                    map.put(prefixSum, i);
                }
            }
            System.out.println(maxLen);
        }
    }