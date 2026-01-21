package DAA.Session_7;

public class LinkedList_Implementation {
    public static class Node{
        int data;
        Node next;
        Node(int data){
            this.data = data;
            this.next = next;
        }
    }

    public static class linkedList{
        Node head = null;

         void insertAtEnd(int data){
            Node now = new Node(data);
            Node temp = head;
            if(head == null){
                head = now;
                return;
            }
            else{
                while(temp.next != null){
                    temp = temp.next;
                }
                temp.next = now;
            }
        }

         void insertAtMid(int data){
            Node slow = head;
            Node fast = head;
            Node prev = null;
            Node add = new Node(data);

            if(head == null){
                head = add;
                return;
            }

            while(fast != null && fast.next != null){
                fast = fast.next.next;
                prev = slow;
                slow = slow.next;
            }
            prev.next = add;
            add.next = slow;
            return;
        }


        void deleteByValue(int val){
             if(head == null){
                 return;
             }
             if(head.data == val){
                 head = head.next;
                 return;
             }
             Node temp = head;
             while(temp.next != null){
                 if(temp.next.data == val){
                     temp.next = temp.next.next;
                     return;
                 }
                 temp = temp.next;
             }
        }


         void display(){
            Node curr = head;
            while(curr != null){
                System.out.print(curr.data+" -> ");
                curr = curr.next;
            }
             System.out.print("NULL");
        }

        public static void main(String[] args) {
            linkedList list = new linkedList();
            list.insertAtEnd(10);
            list.insertAtEnd(20);
            list.insertAtEnd(30);
            list.display();
            list.insertAtMid(15);
            System.out.println();
            list.display();
            list.deleteByValue(20);
            System.out.println();
            list.display();

        }
    }
}
