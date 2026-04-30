package Uni.DAA.Practical_EST;

public class QueueViaLinkedList {
    public static class Node{
        int val;
        Node next;
        Node(int val){
            this.val = val;
            this.next = next;
        }
    }
    public static class queue{
        Node base = new Node(100);
        Node start = base;
        Node end = base;

        public void isEmpty(){
            System.out.println((start == null) ? "Empty" : "Not empty");
        }
        public void peek(){
            System.out.println(start.val);
        }

        public void dequeue(){
            System.out.println(start.val);
            start = start.next;
        }

        public void enqueue(int data){
            Node newNode = new Node(data);
            while(end.next != null){
                end = end.next;
            }
            end.next = newNode;
            end = end.next;
        }

        public void display(){
            Node temp = start.next;
            while(temp != null){
                System.out.print(temp.val + "->");
                temp = temp.next;
            }
            System.out.println("NULL");
        }
    }

    public static void main(String[] args) {
        queue q = new queue();
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.isEmpty();
        q.display();
    }
}
