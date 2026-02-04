package DAA.Experiment_4;

public class AddinHeap {
    static final int MAX = 100;
    static int[] heap = new int[MAX];
    static int size = 0;

    static void heapifyUp(int i){
        while(i > 0 && heap[(i - 1) / 2] > heap[i]){
            int temp = heap[i];
            heap[i] = heap[(i - 1) / 2];
            heap[(i - 1) / 2] = temp;
            i = (i - 1) / 2;
        }
    }

    static void insert(int val) {
        if(size == MAX){
            System.out.print("overflow");
            return;
        }

        heap[size] = val;
        size++;
        heapifyUp(size - 1);
    }

    public static void main(String[] args) {
        insert(5);
        insert(3);
        insert(10);
        insert(1);

        for(int i = 0; i < size; i++){
            System.out.print(heap[i] + " ");
        }
    }
}
