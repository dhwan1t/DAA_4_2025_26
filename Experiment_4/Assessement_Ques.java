package DAA.Experiment_4;

public class Assessement_Ques {
    static int[] heap;
    static int size = 0;
    static int K;

    static void heapifyUp(int i) {
        while(i > 0 && heap[(i - 1) / 2] > heap[i]){
            int temp = heap[i];
            heap[i] = heap[(i - 1) / 2];
            heap[(i - 1) / 2] = temp;
            i = (i - 1) / 2;
        }
    }

    static void heapifyDown(int i){
        int smallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if(left < size && heap[left] < heap[smallest]){
            smallest = left;
        }

        if(right < size && heap[right] < heap[smallest]){
            smallest = right;
        }

        if(smallest != i){
            int temp = heap[i];
            heap[i] = heap[smallest];
            heap[smallest] = temp;
            heapifyDown(smallest);
        }
    }

    static void insert(int val){
        if(size < K){
            heap[size] = val;
            size++;
            heapifyUp(size - 1);
        }
        else if (val > heap[0]){
            heap[0] = val;
            heapifyDown(0);
        }
    }

    public static void main(String[] args) {
        int[] scores = {10, 20, 5, 15, 25, 8};
        K = 3;
        heap = new int[K];

        for(int score : scores){
            insert(score);

            i (size < K){
                System.out.println(-1);
            } 
            else{
                System.out.println(heap[0]);
            }
        }
    }
}
