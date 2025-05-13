package task2;


public class ReverseList<T> {
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = this.data;
            this.next = null;
        }
    }

    public Node<T> reverseList(Node<T> head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node<T> newHead = reverseList(head.next);

        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public void printList(Node<T> head) {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}