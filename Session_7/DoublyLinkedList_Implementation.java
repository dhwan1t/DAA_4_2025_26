package DAA.Session_7;

public class DoublyLinkedList_Implementation {
    public static class Node {
        int data;
        Node next;
        Node prev;
        Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    public static class doublyLinkedList {
        Node head = null;

        void insertAtEnd(int data) {
            Node now = new Node(data);
            if (head == null) {
                head = now;
                return;
            }
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = now;
            now.prev = temp;
        }

        void insertAtMid(int data) {
            Node slow = head;
            Node fast = head;
            Node add = new Node(data);

            if (head == null) {
                head = add;
                return;
            }

            while (fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            add.next = slow;
            add.prev = slow.prev;
            if (slow.prev != null) {
                slow.prev.next = add;
            } else {
                head = add;
            }
            slow.prev = add;
        }

        void deleteByValue(int val) {
            if (head == null) {
                return;
            }
            if (head.data == val) {
                head = head.next;
                if (head != null) {
                    head.prev = null;
                }
                return;
            }
            Node temp = head;
            while (temp != null) {
                if (temp.data == val) {
                    if (temp.next != null) {
                        temp.next.prev = temp.prev;
                    }
                    if (temp.prev != null) {
                        temp.prev.next = temp.next;
                    }
                    return;
                }
                temp = temp.next;
            }
        }

        void display() {
            Node curr = head;
            while (curr != null) {
                System.out.print(curr.data + " -> ");
                curr = curr.next;
            }
            System.out.print("NULL");
        }

        public static void main(String[] args) {
            doublyLinkedList list = new doublyLinkedList();
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

