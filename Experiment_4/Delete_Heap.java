package DAA.Experiment_4;

public class Delete_Heap {
    static final int MAX = 100;
    static int[] heap = new int[MAX];
    static int size = 0;

    static void heapifyDown(int i) {
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
        heap[size] = val;
        size++;
        int i = size - 1;

        while(i > 0 && heap[(i - 1) / 2] > heap[i]){
            int temp = heap[i];
            heap[i] = heap[(i - 1) / 2];
            heap[(i - 1) / 2] = temp;
            i = (i - 1) / 2;
        }
    }

    static void deleteRoot(){
        if(size == 0){
            System.out.print("no element");
            return;
        }

        heap[0] = heap[size - 1];
        size--;
        heapifyDown(0);
    }

    public static void main(String[] args) {
        insert(4);
        insert(2);
        insert(7);
        insert(1);

        deleteRoot();

        for(int i = 0; i < size; i++){
            System.out.print(heap[i] + " ");
        }
    }
}
