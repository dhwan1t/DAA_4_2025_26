package DAA.Experiment_4;

public class Min_Heap {
    static final int MAX = 100;
    static int[] heap = new int[MAX];
    static int heapSize = 0;

    static void heapifyDown(int i){
        int smallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if(left < heapSize && heap[smallest] > heap[left]){
            smallest = left;
        }

        if(right < heapSize && heap[smallest] > heap[right]){
            smallest = right;
        }

        if(smallest != i){
            int temp = heap[i];
            heap[i] = heap[smallest];
            heap[smallest] = temp;
            heapifyDown(smallest);
        }
    }

    static void heapifyUp(int i){
        while(i > 0 && heap[(i - 1) / 2] > heap[i]) {
            int temp = heap[(i - 1) / 2];
            heap[(i - 1) / 2] = heap[i];
            heap[i] = temp;
            i = (i - 1) / 2;
        }
    }

    static void insert(int val){
        if(heapSize == MAX){
            System.out.print("overflow");
            return;
        }

        heap[heapSize] = val;
        heapSize++;
        heapifyUp(heapSize - 1);
    }

    static void deleteNode(){
        if(heapSize == 0){
            System.out.print("no element");
            return;
        }

        heap[0] = heap[heapSize - 1];
        heapSize--;
        heapifyDown(0);
    }

    public static void main(String[] args) {
        insert(2);
        insert(1);
        insert(0);
        deleteNode();
        deleteNode();

        for(int i = 0; i < heapSize; i++){
            System.out.print(heap[i] + " ");
        }
    }
}
